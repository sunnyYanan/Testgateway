package senseHuge.gateway.service;

import senseHuge.gateway.Dao.MySQLiteDbHelper;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DataProvider extends ContentProvider {
	MySQLiteDbHelper dbHelper;
	SQLiteDatabase db;
	public static final String AUTHORITY = "senseHuge.gateway.service.1";

	/**
	 * A uri to do operations on cust_master table. A content provider is
	 * identified by its uri
	 */
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/Telosb");

	/** Constants to identify the requested operation */
	private static final int MESSAGE = 1;

	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, MySQLiteDbHelper.TABLEMESSAGE, MESSAGE);
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		db = dbHelper.getWritableDatabase();
		long rowId;
		if (uriMatcher.match(arg0) != MESSAGE) {
			throw new IllegalArgumentException("Unknown URI" + arg0);
		}
		rowId = db.insert(MySQLiteDbHelper.TABLEMESSAGE, null, arg1);
		if (rowId > 0) {
			Uri noteUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(noteUri, null);
			return noteUri;
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new MySQLiteDbHelper(this.getContext());
		return true;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		db = dbHelper.getReadableDatabase();
		Cursor c;
		switch (uriMatcher.match(arg0)) {
		case MESSAGE:
			/*c = db.query(MySQLiteDbHelper.TABLEMESSAGE, arg1, arg2, arg3, null,
					null, null);*/
			c = db.rawQuery("select * from "+MySQLiteDbHelper.TABLEMESSAGE,null);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI" + arg0);
		}
		c.setNotificationUri(getContext().getContentResolver(), arg0);
		return c;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
