package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererEditEvent extends AppCompatActivity {
    TextView event_number;
    DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_edit_event);

        db=new DBManager(this);
        Button Assign_staff=findViewById(R.id.assign_staff);
        Button Remove_staff=findViewById(R.id.remove_staff);
        Button Add_resources=findViewById(R.id.add_resources);
        Button Remove_resources=findViewById(R.id.remove_resources);
        Button Remove_event=findViewById(R.id.remove_event);
        Button Back=findViewById(R.id.back_caterer);
        event_number = (TextView)findViewById(R.id.edit_event_number);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        event_number.setText(event_number.getText().toString() + " "+ event_id );

        Assign_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignStaff();
            }
        });
        Remove_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeStaff();
            }
        });
        Add_resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResources();
            }
        });
        Remove_resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeResources();
            }
        });
        Remove_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeEvent(db);
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prev_page();
            }
        });

    }
    public void assignStaff(){
        Intent intent_assignStaff = new Intent(this,CatererAssignStaff.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_assignStaff.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_assignStaff.putExtra("USER", user);
        intent_assignStaff.putExtra("EVENT_ID",event_id);
        startActivity(intent_assignStaff);
    }
    public void removeStaff(){
        Intent intent_removeStaff = new Intent(this,CatererRemoveStaff.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_removeStaff.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_removeStaff.putExtra("USER", user);
        intent_removeStaff.putExtra("EVENT_ID",event_id);
        startActivity(intent_removeStaff);
    }
    public void addResources(){
        Intent intent_addResources = new Intent(this,CatererAddResources.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_addResources.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_addResources.putExtra("EVENT_ID",event_id);
        intent_addResources.putExtra("USER", user);
        startActivity(intent_addResources);
    }

    public void removeResources(){
        Intent intent_removeResources = new Intent(this,CatererRemoveResources.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_removeResources.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_removeResources.putExtra("EVENT_ID",event_id);
        intent_removeResources.putExtra("USER", user);
        startActivity(intent_removeResources);
    }
    public void removeEvent(DBManager db){
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        db.deleteEvent(event_id);
        Intent intent_removeEvent = new Intent(this,CatererReservedEvents.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_removeEvent.putExtra("USER",user);
        startActivity(intent_removeEvent);
    }
    public void prev_page(){
        Intent intent_prev = new Intent(this, (Class) getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_prev.putExtra("USER", user);
        startActivity(intent_prev);
    }

}
