package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererReservedEvents extends AppCompatActivity {
    Spinner reserved_events;
    DBManager db;
    List <String> eventID = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_reserved_events);

        db = new DBManager(this);
        Button view_details=(Button) findViewById(R.id.reserved_events_details_caterer);
        Button Edit_event=(Button) findViewById(R.id.edit_event_caterer);
        Button back_event=(Button) findViewById(R.id.reserved_events_back);
        reserved_events = (Spinner) findViewById(R.id.caterer_reserved_events_spinner);
        final List<Event> reserved = db.retrieveReserved();
        for(Event e: reserved)
        {
            String v = Integer.toString(e.getEvent_id());
            eventID.add(v);

        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererReservedEvents.this,
                R.layout.spinner_item,eventID);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reserved_events.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        //TODO: view_details function

        Edit_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEvent();
            }
        });
        back_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backEvent();
            }
        });
        view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_details();
            }
        });

    }

    public void view_details(){
        //Probably need to create a new view and activity for Caterer view reserved event details
        String select = reserved_events.getSelectedItem().toString();
        int selected_event = Integer.parseInt(select);
        Intent intent_viewDetails = new Intent(this,UserStaffReservedEventDetails.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_viewDetails.putExtra("EVENT_ID",selected_event);
        intent_viewDetails.putExtra("USER", user);
        startActivity(intent_viewDetails);
    }
    public void editEvent(){
        String select = reserved_events.getSelectedItem().toString();
        int selected_event = Integer.parseInt(select);
        Intent intent_editEvent = new Intent(this,CatererEditEvent.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_editEvent.putExtra("EVENT_ID",selected_event);
        intent_editEvent.putExtra("USER", user);
        startActivity(intent_editEvent);
    }
    public void backEvent(){
        Intent intent_backEvent = new Intent(this,CatererHomepage.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_backEvent.putExtra("USER", user);
        startActivity(intent_backEvent);
    }
}
