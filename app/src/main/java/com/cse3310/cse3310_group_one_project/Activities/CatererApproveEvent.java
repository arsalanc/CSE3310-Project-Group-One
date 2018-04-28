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
import java.util.Arrays;
import java.util.List;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererApproveEvent extends AppCompatActivity {
    Spinner hall;
    DBManager db;
    List<String> eventID = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_approve_event);
        //TODO: assign hall  to event and reserve event after submit button click
        Button back_button = findViewById(R.id.reserve_event_back);
        Button submit_button = findViewById(R.id.reserve_event_submit);
        ArrayAdapter<String> myAdapter;
        int event_id = (Integer) getIntent().getSerializableExtra("EVENT_ID");
        db = new DBManager(this);
        Event e = db.retrieveEvent(event_id);
        int partySize = e.getParty_size();
        String[] halls_array = getResources().getStringArray(R.array.Halls);
        List<String> list = new ArrayList<String>(Arrays.asList(halls_array));
        if(partySize > 25){
            //arlington, liberty, maverick
            list.remove("KC");
            list.remove("Shard");
            halls_array = list.toArray(new String[0]);
        }
        if(partySize > 50 ){
            //liberty, maverick
            list.remove("Arlington");
            halls_array = list.toArray(new String[0]);
        }
        if(partySize > 75 && partySize <= 100){
            //maverick
            list.remove("Liberty");
            halls_array = list.toArray(new String[0]);
        }

        myAdapter = new ArrayAdapter<String>(CatererApproveEvent.this,
                R.layout.spinner_item,halls_array);


        hall = findViewById(R.id.select_hall);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hall.setAdapter(myAdapter);
        db = new DBManager(this);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitButton(db);
            }
        });
    }

    public void backButton(){
        Intent intent_back = new Intent(this,CatererRequestedEvents.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
    public void submitButton(DBManager db){
        User user = (User) getIntent().getSerializableExtra("USER");
        String select = hall.getSelectedItem().toString();
        if(select.equalsIgnoreCase("Select a Hall"))
        {
            Toast.makeText(this, "You must select a hall first", Toast.LENGTH_LONG).show();
            return;
        }
        int event_id =  (int) getIntent().getSerializableExtra("EVENT_ID");
        Event e = db.retrieveEvent(event_id);
        db.deleteEvent(event_id);
        e.setHall(select);
        e.setCaterer_id(user.getId());
        e.setEvent_id(e.getEvent_id());
        db.addNewEvent(e);
        Intent intent_submit = new Intent(this,CatererRequestedEvents.class);
        intent_submit.putExtra("USER", user);
        startActivity(intent_submit);

    }
}
