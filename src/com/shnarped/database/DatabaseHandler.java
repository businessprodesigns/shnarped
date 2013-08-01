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
	
	// Table User
    public static final String USER_TABLE = "User";
    // User Table Columns names
    public static final String USER_ID = "id";
    public static final String USER_FRIST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL= "email";
    public static final String USER_TWITTER = "twitter";
    public static final String USER_AVATAR = "avatar";
    public static final String USER_GENDER = "gender";
    public static final String USER_BIRTHDATE = "birthdate";
    public static final String USER_VERIFIED = "verified";
    public static final String USER_FAV_TEAM_ID = "favorite_team_id";
    public static final String USER_FAV_TEAM = "favorite_team";
    public static final String USER_POUND_COUNT = "pound_count";
    public static final String USER_FOLLOWING = "following";
    public static final String USER_PUSH = "push";
    public static final String USER_PLAYER_ID = "player_id";
    //public static final String USER_PLAYER = "player";
    
 // Table Player
    public static final String PLAYER_TABLE = "Player";
    // User Table Columns names
    public static final String PLAYER_ID = "id";
    public static final String PLAYER_FRIST_NAME = "first_name";
    public static final String PLAYER_LAST_NAME = "last_name";
    public static final String PLAYER_BIRTHDATE = "birthdate";
    public static final String PLAYER_HOMETOWN = "hometown";
    public static final String PLAYER_IMAGE = "image";
    public static final String PLAYER_POSITION = "position";
    public static final String PLAYER_JERSEY = "jersey";
    public static final String PLAYER_WEIGHT = "weight";
    public static final String PLAYER_WEIGHT_METRIC = "weight_metric";
    public static final String PLAYER_HEIGHT = "height";
    public static final String PLAYER_HEIGHT_METRIC = "height_metric";
    public static final String PLAYER_SHOOTS = "shoots";
    public static final String PLAYER_CAP_HIT = "cap_hit";
    public static final String PLAYER_CONT_THRU = "contact_thru";
    public static final String PLAYER_CURR_TEAM_ID = "current_team_id";
    public static final String PLAYER_CURRENT_TEAM = "current_team";
    public static final String PLAYER_QUS = "questions";
    public static final String PLAYER_Q_ANS = "questions_answered";
	
	public DatabaseHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		_context = context;
		DB_PATH = _context.getDatabasePath(DB_NAME).getAbsolutePath();
		Log.i("DB",DB_NAME);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//createDatabase = true;
		String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                + USER_FRIST_NAME + " TEXT, "
                + USER_LAST_NAME + " TEXT, " 
                + USER_EMAIL + " TEXT , "
                + USER_TWITTER + " TEXT, "
                + USER_AVATAR +  " TEXT, "
                + USER_GENDER + " TEXT, "
                + USER_BIRTHDATE + " TEXT, "
                + USER_VERIFIED + " INTEGER, "
                + USER_FAV_TEAM_ID + " TEXT ,"
                + USER_FAV_TEAM + " TEXT, "
                + USER_POUND_COUNT + " TEXT, "
                + USER_FOLLOWING +  " TEXT, "
                + USER_PUSH + " TEXT, "
                + USER_PLAYER_ID + " TEXT, "
                + " )";
		
		String CREATE_PLAYER_TABLE = "CREATE TABLE " + PLAYER_TABLE + "("
                        + PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                        + PLAYER_FRIST_NAME + " TEXT, "
                        + PLAYER_LAST_NAME + " TEXT, " 
                        + PLAYER_BIRTHDATE + " TEXT , "
                        + PLAYER_HOMETOWN + " TEXT, "
                        + PLAYER_IMAGE +  " TEXT, "
                        + PLAYER_POSITION + " TEXT, "
                        + PLAYER_JERSEY + " TEXT, "
                        + PLAYER_WEIGHT + " INTEGER, "
                        + PLAYER_WEIGHT_METRIC + " TEXT ,"
                        + PLAYER_HEIGHT + " TEXT, "
                        + PLAYER_HEIGHT_METRIC + " TEXT, "
                        + USER_FOLLOWING +  " TEXT, "
                        + USER_PUSH + " TEXT, "
                        + USER_PLAYER_ID + " TEXT, "
                        + " )";
		
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
