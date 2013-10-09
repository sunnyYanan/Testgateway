package senseHuge.Dao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import senseHuge.model.TelosbPackage;
import android.R.integer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TelosbDao {
	SQLiteDatabase db;
	public Context context;
	DBHelper dbHelper ;
	SimpleDateFormat   simpleDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss");     
	
	public TelosbDao(Context context) {
		super();
		this.context = context;
		dbHelper = new DBHelper(context);
		dbHelper.open();

		String sql = "CREATE TABLE IF NOT EXISTS Telosb (id integer primary key AutoIncrement,message varchar(300),Ctype varchar(10),NodeID varchar(10),status varchar(20),receivetime varchar(30));";
		dbHelper.execSQL(sql);
		
		/*String deleteSql = "INSERT INTO Telosb VALUES(4,'2004/05/04',datetime('now'))";
		dbHelper.execSQL(deleteSql);*/
	
		dbHelper.closeConnection();
	}
	
	public  boolean insertTelosbPackage(){
		    Date date = new Date();
		    System.out.println(date.toString());
	    
	        dbHelper.open();  
	  
	        ContentValues values = new ContentValues(); // 相当于map  
	  
	        values.put("message", "test");  
	        values.put("Ctype", "C4");  
	       	        values.put("status", "已上传");  
	        values.put("receivetime", simpleDateFormat.format(date));  
	  
	        dbHelper.insert("Telosb", values);  
	  
	        dbHelper.closeConnection();  
		return true;
	}
	public  boolean insertTelosbPackage(TelosbPackage telosbPackage){
		Date date = new Date();
		System.out.println(date.toString());
		
		dbHelper.open();  
		ContentValues values = new ContentValues(); // 相当于map  
		values.put("message", telosbPackage.getMessage());  
		values.put("Ctype", telosbPackage.getCtype());  
		values.put("NodeID", telosbPackage.nodeID);  
		
		values.put("status", telosbPackage.getStatus());  
		values.put("receivetime", simpleDateFormat.format(date));  
		dbHelper.insert("Telosb", values);  
		dbHelper.closeConnection();  
		return true;
	}
	
	public List<TelosbPackage> findUploadList(){
		List<TelosbPackage> telosbPackages = new ArrayList<TelosbPackage>();
		dbHelper.open();
	//	dbHelper.findList(distinct, tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
		//dbHelper.findList(false, "user", new String[] { "id", "username", "password" }, "username?", new String[] { "test" }, null, null, "id desc", null)
	//	Cursor returnCursor =  dbHelper.findList(false, "Telosb", new String[] { "id", "message", "Ctype","status","receivetime" }, "Ctype?", new String[] { "C4" }, null, null, "id desc", null);
//		Cursor returnCursor = dbHelper.findList("Telosb",new String[] {"id", "message", "Ctype","status","receivetime"}, "Ctype = 'C4' and status='已上传'", null,null, null, "id desc");
		Cursor returnCursor = dbHelper.findList("Telosb",new String[] {"id", "message", "Ctype","NodeID","status","receivetime"}, "status='已上传'", null,null, null, "id desc");
		while (returnCursor.moveToNext()) {  
			    TelosbPackage telosbPackage = new TelosbPackage();
	            int id = returnCursor.getInt(returnCursor.getColumnIndexOrThrow("id"));  
//	            String id = returnCursor.getString(returnCursor.getColumnIndexOrThrow("id"));  
	            String message = returnCursor.getString(returnCursor.getColumnIndexOrThrow("message"));  
	            String ctype = returnCursor.getString(returnCursor.getColumnIndexOrThrow("Ctype"));  
	            String nodeID = returnCursor.getString(returnCursor.getColumnIndexOrThrow("NodeID"));  
	            String status = returnCursor.getString(returnCursor.getColumnIndexOrThrow("status"));  
	            String receivetime = returnCursor.getString(returnCursor.getColumnIndexOrThrow("receivetime"));  
	            System.out.println("id="+id+";message="+message+";Ctype="+ctype+";receivetime="+receivetime+";status="+status);  
	            telosbPackage.setCtype(ctype);
	            telosbPackage.setNodeID(nodeID);
	            telosbPackage.setId(id);
	            telosbPackage.setMessage(message);
	            try {
					telosbPackage.setReceivetime(simpleDateFormat.parse(receivetime));
				} catch (ParseException e) {
					e.printStackTrace();
				}
	            telosbPackage.setStatus(status);
	            telosbPackages.add(telosbPackage);
	        }  
		
		dbHelper.closeConnection();
	return telosbPackages;
	}
	public List<TelosbPackage> findUnUploadList(){
		List<TelosbPackage> telosbPackages = new ArrayList<TelosbPackage>();
		dbHelper.open();
		Cursor returnCursor = dbHelper.findList("Telosb",new String[] {"id", "message", "Ctype","NodeID","status","receivetime"}, "status='未上传'", null,null, null, "id desc");
		while (returnCursor.moveToNext()) {  
			TelosbPackage telosbPackage = new TelosbPackage();
			int id = returnCursor.getInt(returnCursor.getColumnIndexOrThrow("id"));  
            String message = returnCursor.getString(returnCursor.getColumnIndexOrThrow("message"));  
			String ctype = returnCursor.getString(returnCursor.getColumnIndexOrThrow("Ctype"));  
			 String nodeID = returnCursor.getString(returnCursor.getColumnIndexOrThrow("NodeID"));  
			String status = returnCursor.getString(returnCursor.getColumnIndexOrThrow("status"));  
			String receivetime = returnCursor.getString(returnCursor.getColumnIndexOrThrow("receivetime"));  
			System.out.println("id="+id+";message="+message+";Ctype="+ctype+";receivetime="+receivetime+";status="+status);  
			telosbPackage.setCtype(ctype);
			telosbPackage.setId(id);
			telosbPackage.setNodeID(nodeID);
			telosbPackage.setMessage(message);
			try {
				telosbPackage.setReceivetime(simpleDateFormat.parse(receivetime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			telosbPackage.setStatus(status);
			telosbPackages.add(telosbPackage);
		}  
		
		dbHelper.closeConnection();
		return telosbPackages;
	}
	
	public boolean updateTelosbPackageStatus(TelosbPackage telosbPackage){
		dbHelper.open();
		ContentValues initialValues = new ContentValues();
		initialValues.put("status", "未上传"); //更新的字段和值
	//	dbHelper.update("user", initialValues, "id = '1'", null); //第三个参数为 条件语句
		boolean result = dbHelper.update("Telosb", initialValues, "id = '"+telosbPackage.getId()+"'", null);
		dbHelper.closeConnection();
		return result;
	}
	
	
	
	public boolean deleteTelosbPackage(TelosbPackage telosbPackage){
		dbHelper.open();
		dbHelper.delete("Telosb", "id = '"+ telosbPackage.getId() +"'", null);
		dbHelper.closeConnection();
		return true;
		
	}
	
	public List<TelosbPackage> selectTelosbPackage(TelosbPackage telosbPackage){
		return null;
	}
	
	public boolean updateTelosbPackage(TelosbPackage telosbPackage){
		return true;
	}

}
