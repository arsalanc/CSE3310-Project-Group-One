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
    private int mYear = -1, mMonth = -1, mDay = -1, mHour = -1, mMinute = -1;

    int durCounter = 0, partyCounter = 0;

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
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(UserRequestEvent.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Meal_Types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_type.setAdapter(myAdapter);
        meal_venue = (Spinner) findViewById(R.id.venue_type);
        myAdapter = new ArrayAdapter<String>(UserRequestEvent.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Venue_Type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meal_venue.setAdapter(myAdapter);
        formality = (Spinner) findViewById(R.id.meal_formality);
        myAdapter = new ArrayAdapter<String>(UserRequestEvent.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Formality));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formality.setAdapter(myAdapter);
        drink = (Spinner) findViewById(R.id.drink);
        myAdapter = new ArrayAdapter<String>(UserRequestEvent.this,
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
        if(durCounter == 0)
        {
            Toast.makeText(this, "Select the duration of your event", Toast.LENGTH_LONG).show();
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
            final Calendar c = Calendar.getInstance();
            int current_year = c.get(Calendar.YEAR);
            int current_month = c.get(Calendar.MONTH); // 0 indexed so add 1
            int current_day = c.get(Calendar.DAY_OF_MONTH);

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
                    }, current_year, current_month, current_day);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get current time to set up time picker
            final Calendar c = Calendar.getInstance();
            int current_hour = c.get(Calendar.HOUR_OF_DAY);
            int current_minute = c.get(Calendar.MINUTE);

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
                    }, current_hour, current_minute, false);
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
