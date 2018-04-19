package com.cse3310.cse3310_group_one_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguel on 4/13/18.
 **/

public class DBManager extends SQLiteOpenHelper {

    //User
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

    //Event
    private static final String EVENT_TABLE_NAME = "event_data";
    private static final String KEY_EVENT_ID = "event_id";
    private static final String KEY_OWNER_ID = "owner_id";
    private static final String KEY_CATERER_ID = "caterer_id";
    private static final String KEY_PARTY_SIZE = "event_party_size";
    private static final String KEY_DATE = "event_date";
    private static final String KEY_TIME = "event_time";
    private static final String KEY_DURATION = "event_duration";
    private static final String KEY_MEAL_TYPE = "event_meal_type";
    private static final String KEY_MEAL_VENUE = "event_meal_venue";
    private static final String KEY_FORMALITY = "event_formality";
    private static final String KEY_DRINK_VENUE = "event_venue";
    private static final String KEY_HALL = "event_hall";



    public DBManager(Context context) {
        super(context, DB_NAME, null, Db_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_USERDATA = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                KEY_FNAME + " TEXT, " +
                KEY_LNAME + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_PASS + " TEXT, " +
                KEY_ROLE + " TEXT, " +
                KEY_PHONE + " TEXT "+" )";

        String CREATE_TABLE_EVENTDATA = "CREATE TABLE "+ EVENT_TABLE_NAME + "(" +
                KEY_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                KEY_OWNER_ID + " INTEGER," +
                KEY_CATERER_ID + " INTEGER," +
                KEY_PARTY_SIZE + " INTEGER, " +
                KEY_DATE + " TEXT, " +
                KEY_TIME + " TEXT, " +
                KEY_DURATION + " INTEGER, " +
                KEY_MEAL_TYPE + " TEXT, " +
                KEY_MEAL_VENUE + " TEXT, " +
                KEY_FORMALITY + " TEXT, " +
                KEY_DRINK_VENUE + " TEXT, " +
                KEY_HALL + " TEXT " + ")";

        db.execSQL(CREATE_TABLE_USERDATA);
        db.execSQL(CREATE_TABLE_EVENTDATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ EVENT_TABLE_NAME);
        onCreate(db);
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+ " = "+id, null);
    }
    public void deleteEvent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EVENT_TABLE_NAME, KEY_EVENT_ID+ " = "+id, null);
    }

    public void addNewUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, user.getFname());
        values.put(KEY_LNAME, user.getLname());
        values.put(KEY_EMAIL, user.getUsername());
        values.put(KEY_PASS, user.getPassword());
        values.put(KEY_ROLE, user.getAccountType());
        values.put(KEY_PHONE, user.getPhoneNumber());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public UserModel retrieveUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + TABLE_NAME + " WHERE " + KEY_EMAIL + " = \""
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

    public void addNewEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OWNER_ID, event.getOwner_id());
        values.put(KEY_CATERER_ID, event.getCaterer_id());
        values.put(KEY_PARTY_SIZE,event.getParty_size());
        values.put(KEY_DATE,event.getDate());
        values.put(KEY_TIME,event.getTime());
        values.put(KEY_DURATION,event.getDuration());
        values.put(KEY_MEAL_TYPE,event.getMeal_type());
        values.put(KEY_MEAL_VENUE,event.getMeal_venue());
        values.put(KEY_FORMALITY,event.getFormality());
        values.put(KEY_DRINK_VENUE,event.getDrink_venue());
        values.put(KEY_HALL,event.getHall());
        db.insert(EVENT_TABLE_NAME,null,values);
        db.close();

    }

    private Event build_event(Cursor cursor)
    {
        Event event = new Event();
        if(cursor.moveToFirst()){
            event.setEvent_id(cursor.getInt(cursor.getColumnIndex(KEY_EVENT_ID)));
            event.setOwner_id(cursor.getInt(cursor.getColumnIndex(KEY_OWNER_ID)));
            event.setCaterer_id(cursor.getInt(cursor.getColumnIndex(KEY_CATERER_ID)));
            event.setParty_size(cursor.getInt(cursor.getColumnIndex(KEY_PARTY_SIZE)));
            event.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            event.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            event.setDuration(cursor.getInt(cursor.getColumnIndex(KEY_DURATION)));
            event.setMeal_type(cursor.getString(cursor.getColumnIndex(KEY_MEAL_TYPE)));
            event.setMeal_venue(cursor.getString(cursor.getColumnIndex(KEY_MEAL_VENUE)));
            event.setFormality(cursor.getString(cursor.getColumnIndex(KEY_FORMALITY)));
            event.setDrink_venue(cursor.getString(cursor.getColumnIndex(KEY_DRINK_VENUE)));
            event.setHall(cursor.getString(cursor.getColumnIndex(KEY_HALL)));
        } else{
            event = null;
        }
        return event;
    }

    public Event retrieveEvent(int eventID){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * from " + EVENT_TABLE_NAME + " WHERE " + KEY_EVENT_ID + " = \""
                + eventID + "\";";
        Cursor cursor = db.rawQuery(query, null);
        Event event = build_event(cursor);

        return event;
    }

    public List<Event> retrieveRequests(){
        List<Event> getRequests= new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + EVENT_TABLE_NAME + " WHERE " + KEY_HALL + " "
                + "ISNULL" + ";";

        Cursor cursor = db.rawQuery(query, null);


        while (!cursor.isAfterLast()) {
            Event event = build_event(cursor);
            getRequests.add(event);
            cursor.moveToNext();
        }

        return getRequests;
    }

    public List<Event> retrieveRequestsByUserID(int user_id){
        List<Event> getRequests= new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + EVENT_TABLE_NAME + " WHERE ( " + KEY_OWNER_ID + " = '" + user_id + "') and ( " + KEY_HALL + " "
                + "ISNULL" + ");";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                //Event event = build_event(cursor);
                Event event = new Event();
                event.setEvent_id(cursor.getInt(cursor.getColumnIndex(KEY_EVENT_ID)));
                event.setOwner_id(cursor.getInt(cursor.getColumnIndex(KEY_OWNER_ID)));
                event.setCaterer_id(cursor.getInt(cursor.getColumnIndex(KEY_CATERER_ID)));
                event.setParty_size(cursor.getInt(cursor.getColumnIndex(KEY_PARTY_SIZE)));
                event.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                event.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                event.setDuration(cursor.getInt(cursor.getColumnIndex(KEY_DURATION)));
                event.setMeal_type(cursor.getString(cursor.getColumnIndex(KEY_MEAL_TYPE)));
                event.setMeal_venue(cursor.getString(cursor.getColumnIndex(KEY_MEAL_VENUE)));
                event.setFormality(cursor.getString(cursor.getColumnIndex(KEY_FORMALITY)));
                event.setDrink_venue(cursor.getString(cursor.getColumnIndex(KEY_DRINK_VENUE)));
                event.setHall(cursor.getString(cursor.getColumnIndex(KEY_HALL)));
                getRequests.add(event);
                cursor.moveToNext();
            }
        }
        return getRequests;
    }

}