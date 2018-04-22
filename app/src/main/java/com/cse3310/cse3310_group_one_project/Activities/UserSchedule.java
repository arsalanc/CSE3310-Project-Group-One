package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserSchedule extends AppCompatActivity {
    Spinner event_schedule;
    DBManager db;
    List <String> eventID = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule);
        User user = (User) getIntent().getSerializableExtra("USER");

        db = new DBManager(this);
        final List<Event> requests = db.retrieveReserved();
        Button back_button = (Button) findViewById(R.id.user_schedule_back);
        Button view_details_button = (Button) findViewById(R.id.view_details_schedule);
        event_schedule = (Spinner) findViewById(R.id.user_schedule_spinner);

        for(Event e: requests)
        {
            String v = Integer.toString(e.getEvent_id());
            eventID.add(v);

        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(UserSchedule.this,
                R.layout.spinner_item,eventID);
        event_schedule.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });
        view_details_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_details(db);
            }
        });
    }

    public void back_button(){
        Intent intent_back = new Intent(this, UserHomepage.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }

    public void view_details(DBManager db){
        if(event_schedule.getSelectedItem() == null)
        {
            Toast.makeText(this, "There are no upcoming events on the schedule", Toast.LENGTH_LONG).show();
            return;
        }
        //TODO: get event id from spinner, parse, send as extra
        String select = event_schedule.getSelectedItem().toString();
        int selected_event = Integer.parseInt(select);
        Intent intent_viewDetails = new Intent(this,UserEventDetails.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_viewDetails.putExtra("EVENT_ID",selected_event);
        intent_viewDetails.putExtra("PREVIOUS_PAGE", UserSchedule.class);
        intent_viewDetails.putExtra("USER", user);
        startActivity(intent_viewDetails);
    }
}
