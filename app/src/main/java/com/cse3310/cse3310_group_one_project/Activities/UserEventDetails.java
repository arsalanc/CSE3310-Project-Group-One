package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserEventDetails extends AppCompatActivity {
    TextView party_size,date,time,duration,meal_type,venue_type,formality,drink;
    DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        setContentView(R.layout.activity_user_event_details);
        //TODO: display event information, cancel event on cancel button click
        party_size = findViewById(R.id.party_size_text);
        date = findViewById(R.id.date_text);
        time = findViewById(R.id.time_text);
        duration = findViewById(R.id.duration_text);
        meal_type = findViewById(R.id.meal_type_text);
        venue_type = findViewById(R.id.venue_type_text);
        formality = findViewById(R.id.formality_text);
        drink = findViewById(R.id.drink_text);

        db = new DBManager(this);


        set_text(db);
        Button back_button = findViewById(R.id.event_details_back_user);
        Button cancel_event = findViewById(R.id.cancel_event_user);
        super.onCreate(savedInstanceState);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });

       cancel_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel_event(db);
            }
        });
    }

    public void back_button(){
        return_to_previous_page();
    }
    public void cancel_event(DBManager db){
        Intent intent_cancel;
        int event_id = (Integer) getIntent().getSerializableExtra("EVENT_ID");
        db.deleteEvent(event_id);
        return_to_previous_page();
    }

    public void return_to_previous_page()
    {
        Intent intent_back;
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back = new Intent(this, (Class) getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("EVENT_ID", getIntent().getSerializableExtra("EVENT_ID"));
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
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
    }
}
