package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserRequestEventActivity extends AppCompatActivity {
    EditText party_size, date,time, duration, meal_type, meal_venue;
    DBManager db;
    Spinner ampm,formality,drink;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_event);

        db = new DBManager(this);

        Button cancel = (Button) findViewById(R.id.request_event_cancel);
        Button confirm = (Button) findViewById(R.id.request_event_confirm);

        party_size = (EditText) findViewById(R.id.party_size);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        duration = (EditText) findViewById(R.id.duration);
        meal_type = (EditText) findViewById(R.id.meal_type);
        meal_venue = (EditText) findViewById(R.id.venue_type);
        formality = (Spinner) findViewById(R.id.meal_formality);
        drink = (Spinner) findViewById(R.id.drink);
        ampm = (Spinner)findViewById(R.id.ampm);
        //String AmPm = ampm.getSelectedItem().toString();
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

        int partySize = Integer.parseInt(party_size.getText().toString());
        String Date = date.getText().toString();
        String Time = time.getText().toString() + ampm.getSelectedItem().toString();
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
}
