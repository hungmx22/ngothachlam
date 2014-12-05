package com.example.lab09_p01_dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	// Khai bao CSDL
	static final String DATABASE_NAME = "MyDB";
	static final int DATABASE_VERSION = 1;
	// Khai bao ten Bang se tao ra
	static final String TABLE_ACCOUNT = "Account";
	// Khai bao cac cot trong bang
	static final String ACCOUNT_ID = "id";
	static final String ACCOUNT_NAME = "Username";
	static final String ACCOUNT_PASS = "Password";
	static final String ACCOUNT_ADDRESS = "Address";
	// Khai bao lenh tao ra CAC bang trong CSDL
	static final String DATABASE_CREATE 
		= "CREATE TABLE " + TABLE_ACCOUNT
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "Username	TEXT NOT NULL,"
			+ "Password	TEXT NOT NULL,"
			+ "Address  TEXT);";
	
	String[] allColumns = {ACCOUNT_ID, ACCOUNT_NAME, ACCOUNT_PASS, ACCOUNT_ADDRESS};

	// Lop Helper
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			// Ham check DATABASE_NAME ton tai chua, neu chua ton tai, goi onCreate, neu ton tai roi,
			// Check tiep DATABASE_VERSION, neu verion khac, goi onUpgrade hoac onDowngrade
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
			onCreate(db);
		}
	}

	final Context context;
	DatabaseHelper DBHelper;
	SQLiteDatabase db;

	// Constructor
	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {
		// Lenh thuc su tao ra DATABASE
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}

	// ---insert a Account into the database---
	public long insertAccount(String username, String password, String address) {
		ContentValues c = new ContentValues();
		c.put(ACCOUNT_NAME, username);
		c.put(ACCOUNT_PASS, password);
		c.put(ACCOUNT_ADDRESS, address);
		return db.insert(TABLE_ACCOUNT, null, c);
	}

	// ---Lay tat ca Accounts---
	public Cursor getAllAccount() {
//		String username = "Thanh";
//		String pass = "1234566";
//		String lelection = ACCOUNT_NAME + " = '" + username + "' AND " + ACCOUNT_PASS + " = '" + pass + "'";
		// WHERE Username = '' AND Password = '' or '1' = '1'	
		
//		String lelection = ACCOUNT_NAME + " = ? AND " + ACCOUNT_PASS + " = ?";
//		String[] args = {username, pass};
//		String order = ACCOUNT_ID + " DESC";
		
		return db.query(TABLE_ACCOUNT, allColumns, null, null, null, null, null);
	}

	// ---retrieves a particular Account---
	public Cursor getAccountById(long accountId) throws SQLException {
		return db.query(true, TABLE_ACCOUNT, allColumns, 
				ACCOUNT_ID + "=" + accountId,
				null, null, null, null, null);
	}
	
	// ---retrieves a particular Account---
	public Cursor getAccountByUsernameAndPassword(String username, String password) throws SQLException {
//		return db.query(true, TABLE_ACCOUNT, allColumnsAccount, 
//				ACCOUNT_NAME + " = '" + username + "' AND " + ACCOUNT_PASS + " = '" + password + "'",
//				null, null, null, null, null);
		
		String sql = "Select * from " + TABLE_ACCOUNT + " WHERE " + ACCOUNT_NAME + " = '" + username + "' AND " + ACCOUNT_PASS + " = '" + password + "'";
		return db.rawQuery(sql, null);
	}

	// ---updates a Account---
	public boolean updateAccount(long accountId, String username, String password, String address) {
		ContentValues c = new ContentValues();
		c.put(ACCOUNT_NAME, username);
		c.put(ACCOUNT_PASS, password);
		c.put(ACCOUNT_ADDRESS, address);
		return db.update(TABLE_ACCOUNT, c, ACCOUNT_ID + "=" + accountId, null) > 0;
	}

	// ---deletes a particular Account---
	public boolean deleteAccount(long accountId) {
		return db.delete(TABLE_ACCOUNT, ACCOUNT_ID + "=" + accountId, null) > 0;
	}
}
