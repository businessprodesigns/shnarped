package com.shnarped.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper{

	private static String DB_DIR = "/data/com.shnarped.activity.MainActivity/databases/";
	private static String DB_NAME = "Shnarped.sqlite";
	private static String DB_PATH = DB_DIR + DB_NAME;
	private static String OLD_DB_PATH = DB_DIR + "old_" + DB_NAME;
	private static int DB_VERSION = 1;
	private final Context _context;
	
	
	private boolean createDatabase = false;
	private boolean upgradeDatabase = false;
	
	public DatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		_context = context;
		DB_PATH = _context.getDatabasePath(DB_NAME).getAbsolutePath();
		Log.i("DB",DB_NAME);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createDatabase = true;
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		upgradeDatabase = true;
		
	}
	public void clearTable(String tablename) {
		SQLiteDatabase rootDb = null;
        this.initializeDataBase();
        try {
        	rootDb = this.getWritableDatabase();
            String query_string = "DELETE FROM " + tablename + ";";
            rootDb.execSQL(query_string);
            Toast.makeText(_context, tablename + "cleaned.", Toast.LENGTH_LONG).show();
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {
            try {
            	this.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
            	rootDb.close();
            }
        }
	}
	public void initializeDataBase() {
		getWritableDatabase();
		
		if (createDatabase) {
			try {
				copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        } else if (upgradeDatabase) {
        	try {
                FileHelper.copyFile(DB_PATH, OLD_DB_PATH);
                copyDataBase();
                SQLiteDatabase old_db = SQLiteDatabase.openDatabase(OLD_DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
                SQLiteDatabase new_db = SQLiteDatabase.openDatabase(DB_PATH,null, SQLiteDatabase.OPEN_READWRITE);
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }
	private void copyDataBase() throws IOException {
		close();
        InputStream myInput = _context.getAssets().open(DB_NAME);
        OutputStream myOutput = new FileOutputStream(DB_PATH);
        FileHelper.copyFile(myInput, myOutput);
        getWritableDatabase().close();
    }

}
