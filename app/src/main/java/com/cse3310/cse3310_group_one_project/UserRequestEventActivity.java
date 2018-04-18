package com.cse3310.cse3310_group_one_project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import android.widget.ArrayAdapter;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserRequestEventActivity extends AppCompatActivity implements
        View.OnClickListener {
    DBManager db;
    Spinner formality, drink, meal_type, meal_venue;
    Button btnDatePicker, btnTimePicker, btn_add, btn_sub;
    EditText txtDate, txtTime;
    TextView duration, party_size;
    private int mYear, mMonth, mDay, mHour, mMinute = -1;
    int durCounter,partyCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_event);

        db = new DBManager(this);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_sub = (Button)findViewById(R.id.btn_sub);

        Button cancel = (Button) findViewById(R.id.request_event_cancel);
        Button confirm = (Button) findViewById(R.id.request_event_confirm);


        meal_type = (Spinner) findViewById(R.id.meal_type);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(UserRequestEventActivity.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Meal_Types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_type.setAdapter(myAdapter);
        meal_venue = (Spinner) findViewById(R.id.venue_type);
        myAdapter = new ArrayAdapter<String>(UserRequestEventActivity.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Venue_Type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_venue.setAdapter(myAdapter);
        formality = (Spinner) findViewById(R.id.meal_formality);
        myAdapter = new ArrayAdapter<String>(UserRequestEventActivity.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Formality));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formality.setAdapter(myAdapter);
        drink = (Spinner) findViewById(R.id.drink);
        myAdapter = new ArrayAdapter<String>(UserRequestEventActivity.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Drink));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drink.setAdapter(myAdapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm(db);
            }
        });
        // I think this is the source of the bug
    }

    public void cancel(){
        Intent intent_cancel = new Intent(this,UserHomepageActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_cancel.putExtra("USER", user);
        startActivity(intent_cancel);
    }

    public void confirm(DBManager db){
        //TODO: Error check information
        if(party_size == null || mMonth == -1 || mDay == -1 || mYear == -1 || mHour == -1
                || mMinute == -1 || duration == null || meal_type == null || meal_venue == null
                || formality == null || drink == null)
        {
            Toast.makeText(this, "MUST FILL IN ALL FIELDS", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent_requestEvent = new Intent(this,UserHomepageActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");

        int partySize = Integer.parseInt(party_size.getText().toString());
        int owner_id = user.getId();
        String Date = Integer.toString(mMonth) + "/" + Integer.toString(mDay) + "/" + Integer.toString(mYear);
        String Time = Integer.toString(mHour) + ":" + Integer.toString(mMinute);
        int Duration = Integer.parseInt(duration.getText().toString() );
        String mealType = meal_type.getSelectedItem().toString();
        String mealVenue = meal_venue.getSelectedItem().toString();
        String Formality = formality.getSelectedItem().toString();
        String Drink = drink.getSelectedItem().toString();
        Event event = new Event(owner_id,partySize,Date,Time,Duration,mealType,mealVenue,Formality,Drink);

        db.addNewEvent(event);

        intent_requestEvent.putExtra("USER", user);
        startActivity(intent_requestEvent);
    }

    // https://www.journaldev.com/9976/android-date-time-picker-dialog
    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH); // 0 indexed so add 1
            mDay = c.get(Calendar.DAY_OF_MONTH);
            String Date = Integer.toString(mMonth) + "/" + Integer.toString(mDay) + "/" + Integer.toString(mYear);
            Log.d("EVAN", Date);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText((monthOfYear+1) + "/" + dayOfMonth + "/" + year);

                            // update date with user selection
                            mMonth = monthOfYear+1;
                            mDay = dayOfMonth;
                            mYear = year;
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);

                            // update hour/minute with user selection
                            mHour = hourOfDay;
                            mMinute = minute;
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

    }
    public void increaseIntegerDuration(View v) {
        durCounter = durCounter + 1;
        displayDuration(durCounter);

    }public void decreaseIntegerDuration(View v) {
        if (durCounter <= 0 ) {
            Toast.makeText(this, "You can't do that.", Toast.LENGTH_LONG).show();
        }
        else{
            durCounter = durCounter - 1;
            displayDuration(durCounter);
        }
    }
    private void displayDuration(int number) {
        duration = (TextView) findViewById(R.id.duration);
        duration.setText("Duration(hrs): " + number);
    }
    public void increaseIntegerPS(View v) {
        partyCounter = partyCounter + 10;
        displayPS(partyCounter);

    }public void decreaseIntegerPS(View v) {
        if (partyCounter <= 0) {
            Toast.makeText(this, "You can't do that.", Toast.LENGTH_LONG).show();
        }
        else{
            partyCounter = partyCounter - 10;
            displayPS(partyCounter);
        }
    }
    private void displayPS(int number) {
        party_size = (TextView) findViewById(R.id.partySize);
        party_size.setText("Party Size: " + number);
    }
}
