package senseHuge.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ���ݿⳣ�ò����ķ�װ��
 * 
 * @author ������
 * 
 */
public class DBHelper {

	private static DatabaseHelper mDbHelper;
	private static SQLiteDatabase mDb;

	private static final String DATABASE_NAME = "telosb.db";

	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	public DBHelper(Context ctx) {
		this.mCtx = ctx;
	}

	public DBHelper open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	/**
	 * �ر�����Դ
	 * 
	 * @author SHANHY
	 */
	public void closeConnection() {
		if (mDb != null && mDb.isOpen())
			mDb.close();
		if (mDbHelper != null)
			mDbHelper.close();
	}

	/**
	 * �������� ����
	 * 
	 * @param tableName
	 *            ����
	 * @param initialValues
	 *            Ҫ������ж�Ӧֵ
	 * @return
	 * @author SHANHY
	 */
	public long insert(String tableName, ContentValues initialValues) {

		return mDb.insert(tableName, null, initialValues);
	}

	/**
	 * ɾ������
	 * 
	 * @param tableName
	 *            ����
	 * @param deleteCondition
	 *            ����
	 * @param deleteArgs
	 *            ������Ӧ��ֵ�����deleteCondition���С������ţ����ô������е�ֵ�滻��һһ��Ӧ��
	 * @return
	 * @author SHANHY
	 */
	public boolean delete(String tableName, String deleteCondition, String[] deleteArgs) {

		return mDb.delete(tableName, deleteCondition, deleteArgs) > 0;
	}

	/**
	 * ��������
	 * 
	 * @param tableName
	 *            ����
	 * @param initialValues
	 *            Ҫ���µ���
	 * @param selection
	 *            ���µ�����
	 * @param selectArgs
	 *            ���������еġ�������Ӧ��ֵ
	 * @return
	 * @author SHANHY
	 */
	public boolean update(String tableName, ContentValues initialValues, String selection, String[] selectArgs) {
		return mDb.update(tableName, initialValues, selection, selectArgs) > 0;
	}

	/**
	 * ȡ��һ���б�
	 * 
	 * @param distinct
	 *            �Ƿ�ȥ�ظ�
	 * @param tableName
	 *            ����
	 * @param columns
	 *            Ҫ���ص���
	 * @param selection
	 *            ����
	 * @param selectionArgs
	 *            �����С������Ĳ���ֵ
	 * @param groupBy
	 *            ����
	 * @param having
	 *            �����������
	 * @param orderBy
	 *            ����
	 * @return
	 * @author SHANHY
	 */
	
	public Cursor findList(boolean distinct, String tableName, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
    
		return mDb.query(distinct, tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
	}
	public Cursor findList(String tableName,String[] columns,String selection,String[] selectionArgs,String groupBy,String having,String orderBy) {

		return mDb.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy);
		}
	/**
	 * ȡ�õ��м�¼
	 * 
	 * @param tableName
	 *            ����
	 * @param columns
	 *            ��ȡ��������
	 * @param selection
	 *            ����
	 * @param selectionArgs
	 *            �����С�������Ӧ��ֵ
	 * @param groupBy
	 *            ����
	 * @param having
	 *            ��������
	 * @param orderBy
	 *            ����
	 * @param limit
	 *            ��������
	 * @param distinct
	 *            �Ƿ�ȥ�ظ�
	 * @return
	 * @throws SQLException
	 * @author SHANHY
	 */
	public Cursor findOne(boolean distinct,String tableName, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) throws SQLException {

		Cursor mCursor = findList(distinct, tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/**
	 * ִ��SQL(������)
	 * 
	 * @param sql
	 * @param args
	 *            SQL�С���������ֵ
	 * @author SHANHY
	 */
	public void execSQL(String sql, Object[] args) {
		mDb.execSQL(sql, args);

	}

	/**
	 * ִ��SQL
	 * 
	 * @param sql
	 * @author SHANHY
	 */
	public void execSQL(String sql) {
		mDb.execSQL(sql);

	}

	/**
	 * �ж�ĳ�ű��Ƿ����
	 * 
	 * @param tabName
	 *            ����
	 * @return
	 */
	public boolean isTableExist(String tableName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}

		try {
			Cursor cursor = null;
			String sql = "select count(1) as c from sqlite_master where type ='table' and name ='" + tableName.trim() + "'";
			cursor = mDb.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}

			cursor.close();
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * �ж�ĳ�ű����Ƿ����ĳ�ֶ�(ע���÷����޷��жϱ��Ƿ���ڣ����Ӧ��isTableExistһ��ʹ��)
	 * 
	 * @param tabName
	 *            ����
	 * @param columnName
	 *            ����
	 * @return
	 */
	public boolean isColumnExist(String tableName, String columnName) {
		boolean result = false;
		if (tableName == null) {
			return false;
		}

		try {
			Cursor cursor = null;
			String sql = "select count(1) as c from sqlite_master where type ='table' and name ='" + tableName.trim() + "' and sql like '%" + columnName.trim() + "%'";
			cursor = mDb.rawQuery(sql, null);
			if (cursor.moveToNext()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					result = true;
				}
			}

			cursor.close();
		} catch (Exception e) {
		}
		return result;
	}

}
