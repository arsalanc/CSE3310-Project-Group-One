package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererRequestedEvents extends AppCompatActivity {
    Spinner select_event;
    List<String> eventID = new ArrayList<String>();
    DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_requested_events);
        User user = (User) getIntent().getSerializableExtra("USER");
        db = new DBManager(this);
        final List<Event> requests = db.retrieveRequests();

        select_event = (Spinner) findViewById(R.id.caterer_requested_events_spinner);

        for(Event e: requests)
        {
            String v = Integer.toString(e.getEvent_id());
            eventID.add(v);

        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererRequestedEvents.this,
                R.layout.spinner_item,eventID);
        select_event.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        //TODO:view_details, reserve and deny buttons
        Button view_details = (Button) findViewById(R.id.requested_event_details);
        Button reserve = (Button) findViewById(R.id.reserve);
        Button deny = (Button) findViewById(R.id.deny);
        Button back = (Button) findViewById(R.id.requested_events_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requested_back();
            }
        });
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reserve();
            }
        });
        view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDetails();
            }
        });
        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deny(db);
            }
        });
    }

    public void viewDetails(){
        if(select_event.getSelectedItem() == null)
        {
            Toast.makeText(this, "You don't have any requested events", Toast.LENGTH_LONG).show();
            return;
        }
        String select = select_event.getSelectedItem().toString();
        int selected_event = Integer.parseInt(select);
        Intent intent_viewDetails = new Intent(this,UserEventDetails.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_viewDetails.putExtra("EVENT_ID",selected_event);
        intent_viewDetails.putExtra("PREVIOUS_PAGE", CatererRequestedEvents.class);
        intent_viewDetails.putExtra("USER", user);
        startActivity(intent_viewDetails);
    }
    public void reserve(){
        if(select_event.getSelectedItem() == null)
        {
            Toast.makeText(this, "You don't have any requested events", Toast.LENGTH_LONG).show();
            return;
        }
        String select = select_event.getSelectedItem().toString();
        int selected_event = Integer.parseInt(select);
        Intent intent_reserve = new Intent(this,CatererApproveEvent.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_reserve.putExtra("EVENT_ID",selected_event);
        intent_reserve.putExtra("USER", user);
        startActivity(intent_reserve);
    }
    public void deny(DBManager db){
        if(select_event.getSelectedItem() == null)
        {
            Toast.makeText(this, "You don't have any requested events", Toast.LENGTH_LONG).show();
            return;
        }
        String select = select_event.getSelectedItem().toString();
        int selected_event = Integer.parseInt(select);
        db.deleteEvent(selected_event);
        Intent intent_deny = new Intent(this,CatererHomepage.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_deny.putExtra("USER",user);
        startActivity(intent_deny);

    }
    public void requested_back(){
        Intent intent_requestBack = new Intent(this,CatererHomepage.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_requestBack.putExtra("USER", user);
        startActivity(intent_requestBack);
    }
}
