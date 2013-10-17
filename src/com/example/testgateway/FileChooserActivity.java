package com.example.testgateway;

import java.io.File;
import java.util.ArrayList;

import com.example.testgateway.FileChooserAdapter.FileInfo;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.view.KeyEvent;
import android.widget.TextView;

public class FileChooserActivity extends Activity {
	
	private GridView mGridView;
	private View mBackView;
	private View mBtExit;
	private TextView mTvPath ;
	
	private String mSdcardRootPath ;  //sdcard 根路�?
	private String mLastFilePath ;    //当前显示的路�?	
	private ArrayList<FileInfo> mFileLists  ;
	private FileChooserAdapter mAdatper ;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.filechooser_show);

		//mSdcardRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();// �õ�sdcardĿ¼
		mSdcardRootPath = "/";
		mBackView = findViewById(R.id.imgBackFolder);
		mBackView.setOnClickListener(mClickListener);
		mBtExit = findViewById(R.id.btExit);
		mBtExit.setOnClickListener(mClickListener);
		
		mTvPath = (TextView)findViewById(R.id.tvPath);
		
		mGridView = (GridView)findViewById(R.id.gvFileChooser);
		mGridView.setEmptyView(findViewById(R.id.tvEmptyHint));
		mGridView.setOnItemClickListener(mItemClickListener);
		setGridViewAdapter(mSdcardRootPath);
	}
	//配置适配�?	
	private void setGridViewAdapter(String filePath) {
		updateFileItems(filePath);
		mAdatper = new FileChooserAdapter(this , mFileLists);
		mGridView.setAdapter(mAdatper);
	}
	//根据路径更新数据，并且�?知Adatper数据改变
	private void updateFileItems(String filePath) {
		mLastFilePath = filePath ;
		mTvPath.setText(mLastFilePath);
		
		if(mFileLists == null)
			mFileLists = new ArrayList<FileInfo>() ;
		if(!mFileLists.isEmpty())
			mFileLists.clear() ;
		
		File[] files = folderScan(filePath);
		if(files == null) 
			return ;
		
		for (int i = 0; i < files.length; i++) {
			if(files[i].isHidden())  // 不显示隐藏文�?				
				continue ;
			String fileAbsolutePath = files[i].getAbsolutePath() ;
			String fileName = files[i].getName();
		    boolean isDirectory = false ;
			if (files[i].isDirectory()){
				isDirectory = true ;
			}
		    FileInfo fileInfo = new FileInfo(fileAbsolutePath , fileName , isDirectory) ;
			mFileLists.add(fileInfo);
		}
		//When first enter , the object of mAdatper don't initialized
		if(mAdatper != null)
		    mAdatper.notifyDataSetChanged();  //重新刷新
	}
	//获得当前路径的所有文�?	
	private File[] folderScan(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		return files;
	}
	
	private View.OnClickListener mClickListener = new  OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.imgBackFolder:
				backProcess();
				break;
			case R.id.btExit :
				setResult(RESULT_CANCELED);
				finish();
			    break ;
			default :
			    	break ;
			}
		}
	};
	
	private AdapterView.OnItemClickListener mItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> adapterView, View view, int position,
				long id) {
			FileInfo fileInfo = (FileInfo)(((FileChooserAdapter)adapterView.getAdapter()).getItem(position));
			if(fileInfo.isDirectory())   //点击项为文件�? 显示该文件夹下所有文�?				
				updateFileItems(fileInfo.getFilePath()) ;
			else if(fileInfo.isPPTFile()){  //是ppt文件 �?则将该路径�?知给调用�?			    
				Intent intent = new Intent();
			    intent.putExtra(Fragment_alertSetting.EXTRA_FILE_CHOOSER , fileInfo.getFilePath());
			    setResult(RESULT_OK , intent);
			    finish();
			}
			else {   //其他文件.....
				toast("���ļ�����");
			}
		}
	};
    
	public boolean onKeyDown(int keyCode , KeyEvent event){
		if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode()
			== KeyEvent.KEYCODE_BACK){
			backProcess();   
			return true ;
		}
		return super.onKeyDown(keyCode, event);
	}
	//返回上一层目录的操作
	public void backProcess(){
		//判断当前路径是不是sdcard路径 �?如果不是，则返回到上�?���?		
		if (!mLastFilePath.equals(mSdcardRootPath)) {  
			File thisFile = new File(mLastFilePath);
			String parentFilePath = thisFile.getParent();
			updateFileItems(parentFilePath);
		} 
		else {   //是sdcard路径 ，直接结�?			
			setResult(RESULT_CANCELED);
			finish();
		}
	}
	private void toast(CharSequence hint){
	    Toast.makeText(this, hint , Toast.LENGTH_SHORT).show();
	}
}