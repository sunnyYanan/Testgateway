package senseHuge.gateway.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteDbHelper extends SQLiteOpenHelper {
	public MySQLiteDbHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		String sql = "create table Telosb(_id integer primary key AutoIncrement,message varchar(300),Ctype varchar(10),NodeID varchar(10),status varchar(20),receivetime varchar(30))";
		String sqlServer = "create table Server(_id integer primary key AutoIncrement,address varchar(50))";
		String alertSetting = "create table AlertSetting(_id integer primary key AutoIncrement,type varchar(10),value varchar(5),path varchar(200))";
		arg0.execSQL(sql);
		arg0.execSQL(sqlServer);
		arg0.execSQL(alertSetting);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
