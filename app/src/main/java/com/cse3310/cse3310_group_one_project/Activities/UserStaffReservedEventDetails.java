package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018?.
 */

public class UserStaffReservedEventDetails extends AppCompatActivity {
    TextView party_size,date,time,duration,meal_type,venue_type,formality,drink,hall;
    DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_staff_reserved_event_details);

        party_size = (TextView) findViewById(R.id.reserved_event_party_size);
        date = (TextView) findViewById(R.id.reserved_event_date);
        time = (TextView) findViewById(R.id.reserved_event_time);
        duration = (TextView) findViewById(R.id.reserved_event_duration);
        meal_type = (TextView) findViewById(R.id.reserved_event_Meal);
        venue_type = (TextView) findViewById(R.id.reserved_event_venue);
        formality = (TextView) findViewById(R.id.reserved_event_formality);
        drink = (TextView) findViewById(R.id.reserved_event_drink);
        hall = (TextView) findViewById(R.id.reserved_event_hall);
        db = new DBManager(this);
        set_text(db);

        Button caterer_info = (Button) findViewById(R.id.caterer_information);
        Button back_button = (Button) findViewById(R.id.event_details_back_user);
        User user = (User) getIntent().getSerializableExtra("USER");
        if(user.getAccountType().equalsIgnoreCase("caterer"))
        {
            caterer_info.setText("User info");
        }
        caterer_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caterer_info();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });
    }
    public void back_button(){
        //TODO: go back to homepage of staff or user based on account type

        User user = (User) getIntent().getSerializableExtra("USER");
        if(user.getAccountType().equalsIgnoreCase("caterer"))
        {
            Intent intent_back = new Intent(this,CatererReservedEvents.class);
            intent_back.putExtra("USER", user);
            startActivity(intent_back);
        }
        finish();
    }
    public void caterer_info(){
        User user = (User) getIntent().getSerializableExtra("USER");

        if(user.getAccountType().equalsIgnoreCase("caterer"))
        {
            Intent intent_userInfo = new Intent(this, CatererViewUserInfo.class);
            int event_id = (Integer) getIntent().getSerializableExtra("EVENT_ID");
            intent_userInfo.putExtra("USER", user);
            intent_userInfo.putExtra("EVENT_ID", event_id);
            startActivity(intent_userInfo);
        }
        else {
            Intent intent_catererInfo = new Intent(this, ViewCatererInfo.class);
            int event_id = (Integer) getIntent().getSerializableExtra("EVENT_ID");
            intent_catererInfo.putExtra("USER", user);
            intent_catererInfo.putExtra("EVENT_ID", event_id);
            startActivity(intent_catererInfo);
        }
    }
    public void set_text(DBManager db){
        int event_id = (Integer) getIntent().getSerializableExtra("EVENT_ID");
        Event e = db.retrieveEvent(event_id);
        party_size.setText(party_size.getText().toString() + " " + e.getParty_size());
        date.setText(date.getText().toString() + " " + e.getDate());
        time.setText(time.getText().toString() + " " + e.getTime());
        duration.setText(duration.getText().toString() + " " + e.getDuration() + " Hours");
        meal_type.setText(meal_type.getText().toString() + " " + e.getMeal_type());
        venue_type.setText(venue_type.getText().toString() + " " + e.getMeal_venue());
        formality.setText(formality.getText().toString() + " " + e.getFormality());
        drink.setText(drink.getText().toString() + " " + e.getDrink_venue());
        hall.setText(hall.getText().toString() + " " + e.getHall());
    }
}
