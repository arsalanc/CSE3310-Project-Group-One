package com.cse3310.cse3310_group_one_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by miguel on 4/13/18.
 **/

public class DBManager extends SQLiteOpenHelper {

    private static final int Db_VERSION = 1;
    private static final String DB_NAME = "users_db";
    private static final String TABLE_NAME = "user_data";
    private static final String KEY_ID = "user_id";
    private static final String KEY_FNAME = "user_fname";
    private static final String KEY_LNAME = "user_lname";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PASS = "user_pass";
    private static final String KEY_ROLE = "user_role";
    private static final String KEY_PHONE = "user_phone";

    public DBManager(Context context) {
        super(context, DB_NAME, null, Db_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USERDATA = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                KEY_FNAME + "TEXT," +
                KEY_LNAME + "TEXT," +
                KEY_EMAIL + "TEXT," +
                KEY_PASS + "TEXT," +
                KEY_ROLE + "TEXT," +
                KEY_PHONE + "TEXT"+")";

        db.execSQL(CREATE_TABLE_USERDATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addNewUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, user.getFname());
        values.put(KEY_FNAME, user.getLname());
        values.put(KEY_EMAIL, user.getUsername());
        values.put(KEY_PASS, user.getPassword());
        values.put(KEY_ROLE, user.getAccountType());
        values.put(KEY_PHONE, user.getPhoneNumber());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public UserModel retrieveUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_NAME + "WHERE " + KEY_EMAIL + "= \""
                + username + "\" AND " + KEY_PASS + "= \"" + password + "\";";

        Cursor cursor = db.rawQuery(query, null);
        UserModel model = new UserModel();
        if (cursor.moveToFirst()) {
            model.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            model.setFname(cursor.getString((cursor.getColumnIndex(KEY_FNAME))));
            model.setLname(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
            model.setUsername(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            model.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
            model.setAccountType(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
            model.setPhoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PHONE)));

        } else {
            model = null;
        }
        return model;
    }
}