package com.cse3310.cse3310_group_one_project.Activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import android.widget.ArrayAdapter;

import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserRequestEvent extends AppCompatActivity implements
        View.OnClickListener {
    DBManager db;
    Spinner formality, drink, meal_type, meal_venue;
    Button btnDatePicker, btnTimePicker, btn_add, btn_sub;
    EditText txtDate, txtTime;
    TextView duration, party_size;
    final Calendar calendar = Calendar.getInstance();
    private int mYear = -1, mMonth = -1, mDay = -1, mHour = -1, mMinute = -1;
    private long timeNow;

    // minimum duration is 2 hours
    int durCounter = 2, partyCounter = 0;

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

        // set duration text on page build
        displayDuration(durCounter);

        // Set up the calendar before we need it to prevent it incrementing every time a user opens the date picker
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        timeNow = calendar.getTimeInMillis();

        Button cancel = (Button) findViewById(R.id.request_event_cancel);
        Button confirm = (Button) findViewById(R.id.request_event_confirm);

        meal_type = (Spinner) findViewById(R.id.meal_type);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(UserRequestEvent.this,
                R.layout.spinner_item2,getResources().getStringArray(R.array.Meal_Types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_type.setAdapter(myAdapter);
        meal_venue = (Spinner) findViewById(R.id.venue_type);
        myAdapter = new ArrayAdapter<String>(UserRequestEvent.this,
                R.layout.spinner_item2,getResources().getStringArray(R.array.Venue_Type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_venue.setAdapter(myAdapter);
        formality = (Spinner) findViewById(R.id.meal_formality);
        myAdapter = new ArrayAdapter<String>(UserRequestEvent.this,
                R.layout.spinner_item2,getResources().getStringArray(R.array.Formality));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formality.setAdapter(myAdapter);
        drink = (Spinner) findViewById(R.id.drink);
        myAdapter = new ArrayAdapter<String>(UserRequestEvent.this,
                R.layout.spinner_item2,getResources().getStringArray(R.array.Drink));
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
        Intent intent_cancel = new Intent(this,UserHomepage.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_cancel.putExtra("USER", user);
        startActivity(intent_cancel);
    }

    public void confirm(DBManager db){
        //TODO: Error check information
        if(mMonth == -1 || mDay == -1 || mYear == -1 )
        {
            Toast.makeText(this, "Select a day for the event", Toast.LENGTH_LONG).show();
            return;
        }
        if (mHour == -1 || mMinute == -1)
        {
            Toast.makeText(this, "Select a time for the event", Toast.LENGTH_LONG).show();
            return;
        }
        if(!isValidTime(mHour, mMinute, durCounter))
        {
            return;
        }
        if(partyCounter == 0)
        {
            Toast.makeText(this, "Select the amount of people coming to your event", Toast.LENGTH_LONG).show();
            return;
        }
        if(meal_type.getSelectedItem().toString().equalsIgnoreCase( "Meal Type"))
        {
            Toast.makeText(this, "Select Meal Type", Toast.LENGTH_LONG).show();
            return;
        }
        else if(meal_venue.getSelectedItem().toString().equalsIgnoreCase( "Venue Type"))
        {
            Toast.makeText(this, "Select Venue Type", Toast.LENGTH_LONG).show();
            return;
        }
        else if(formality.getSelectedItem().toString().equalsIgnoreCase("Meal Formality"))
        {
            Toast.makeText(this, "Select Formality", Toast.LENGTH_LONG).show();
            return;
        }
        else if(drink.getSelectedItem().toString().equalsIgnoreCase( "Drink Type"))
        {
            Toast.makeText(this, "Select Drink Type", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent_requestEvent = new Intent(this,UserHomepage.class);
        User user = (User) getIntent().getSerializableExtra("USER");

        int partySize = partyCounter;
        int owner_id = user.getId();
        String Date = Integer.toString(mMonth) + "/" + Integer.toString(mDay) + "/" + Integer.toString(mYear);
        String Time = Integer.toString(mHour) + ":" + Integer.toString(mMinute);
        int Duration = durCounter;
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

            // Get current date to initialize date picker

            // Add one day to the calendar to give caterers time to approve
            int current_year = calendar.get(Calendar.YEAR);
            int current_month = calendar.get(Calendar.MONTH);
            int current_day = calendar.get(Calendar.DAY_OF_MONTH );

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

                            // update actual calendar date as well, so if user opens date picker again it will go to their previous selection.
                            //  Also used in isValidTime()
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, monthOfYear);
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        }
                    }, current_year, current_month, current_day);
            // Prevent user from selecting dates from today or in the past
            datePickerDialog.getDatePicker().setMinDate(timeNow);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {
            // Get current time to set up time picker
            final Calendar c = Calendar.getInstance();
            int current_hour = 0;
            int current_minute = 0;

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // its probably less of a headache to just error check this when the user
                            //  tries to submit instead every time the user picks a time/duration
                            /*if(!isValidTime(hourOfDay, minute, durCounter))
                            {
                                mHour = -1;
                                mMinute = -1;
                                txtTime.setText("");
                                return;
                            }*/

                            txtTime.setText(hourOfDay + ":" + minute);

                            // update hour/minute with user selection
                            mHour = hourOfDay;
                            mMinute = minute;
                        }
                    }, current_hour, current_minute, true);
            timePickerDialog.show();
        }

    }
    public void increaseIntegerDuration(View v) {
        durCounter = durCounter + 1;
        displayDuration(durCounter);
    }
    public void decreaseIntegerDuration(View v) {
        if (durCounter <= 2 ) {
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
        partyCounter = partyCounter + 5;
        displayPS(partyCounter);

    }public void decreaseIntegerPS(View v) {
        if (partyCounter <= 0 || partyCounter >= 100) {
            Toast.makeText(this, "You can't do that.", Toast.LENGTH_LONG).show();
        }
        else{
            partyCounter = partyCounter - 5;
            displayPS(partyCounter);
        }
    }
    private void displayPS(int number) {
        party_size = (TextView) findViewById(R.id.partySize);
        party_size.setText("Party Size: " + number);
    }

    private boolean isValidTime(int hour, int minute, int duration)
    {
        boolean isSunday = calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
        boolean isWeekend = isSunday || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;

        // opens @ noon on sundays, 7pm any other day
        // hardcoding these 2 vars numbers since it won't change for this project
        int minLimit = isSunday ? 12 : 7;

        // 26 hours total for weekends (2 am next day), 23 for weekdays (11pm same day)
        int maxLimit = isWeekend ? 26 : 23;

        // can do this or add + 1 to hours if minutes > 0, doesn't really matter
        double hourPlusMinutes = hour + (minute / 60.0);

        if(isWeekend && (hourPlusMinutes == 0 && duration == 2))
        {
            return true;
        }
        else if(hourPlusMinutes < minLimit)
        {
            String response = "Halls won't be open until " + (minLimit == 12 ? "12pm." : "7am.");
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            return false;
        }

        // convert minutes to hours and add them to the calculation
        // might just want to remove the option to choose minutes?
        if((hourPlusMinutes + duration) > maxLimit)
        {
            String response = "Event can't extend past " + (maxLimit == 26 ? "2am." : "11pm.");
            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}