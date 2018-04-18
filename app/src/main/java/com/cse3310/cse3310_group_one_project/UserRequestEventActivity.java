package com.cse3310.cse3310_group_one_project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserRequestEventActivity extends AppCompatActivity implements
        View.OnClickListener {
    EditText party_size, duration, meal_type, meal_venue;
    DBManager db;
    Spinner formality,drink;
    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute = -1;

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

        Button cancel = (Button) findViewById(R.id.request_event_cancel);
        Button confirm = (Button) findViewById(R.id.request_event_confirm);

        party_size = (EditText) findViewById(R.id.party_size);
        duration = (EditText) findViewById(R.id.duration);
        meal_type = (EditText) findViewById(R.id.meal_type);
        meal_venue = (EditText) findViewById(R.id.venue_type);
        formality = (Spinner) findViewById(R.id.meal_formality);
        drink = (Spinner) findViewById(R.id.drink);

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
        int partySize = Integer.parseInt(party_size.getText().toString());
        String Date = Integer.toString(mMonth) + "/" + Integer.toString(mDay) + "/" + Integer.toString(mYear);
        String Time = Integer.toString(mHour) + ":" + Integer.toString(mMinute);
        int Duration = Integer.parseInt(duration.getText().toString() );
        String mealType = meal_type.getText().toString();
        String mealVenue = meal_venue.getText().toString();
        String Formality = formality.getSelectedItem().toString();
        String Drink = drink.getSelectedItem().toString();
        Event event = new Event(partySize,Date,Time,Duration,mealType,mealVenue,Formality,Drink);

        db.addNewEvent(event);

        Intent intent_requestEvent = new Intent(this,UserHomepageActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
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
}
