package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.database.Cursor;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererAssignStaff extends AppCompatActivity {
    Spinner available_staff;
    DBManager db;
    List <String> staffNames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_assign_staff);
        //TODO: Submit button functionality
        Button assign_staff_submit = findViewById(R.id.assign_staff_submit);
        Button back_assign_staff = findViewById(R.id.back_assign_staff);
        available_staff = (Spinner) findViewById(R.id.available_staff);
        db = new DBManager(this);
        staffNames=db.retrieveStaff();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAssignStaff.this,
                R.layout.spinner_item,staffNames);

        available_staff.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        back_assign_staff.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignStaffBack();
            }
        }));
        assign_staff_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignStaffSubmit(db);
            }
        });
        
    }
    public void assignStaffSubmit(DBManager db){
        String selected=available_staff.getSelectedItem().toString();
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        // 0 is fname 1 is lname
        String[] name = selected.split("\\s+");
        int staff_id=db.retrieveUserID(name[0],name[1]);
        //adds to table
        db.assignStaff(event_id,staff_id);
        Intent intent_submit = new Intent(this,CatererEditEvent.class);
        intent_submit.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_submit.putExtra("USER", user);
        startActivity(intent_submit);
    }
    public void assignStaffBack(){
        Intent intent_back = new Intent(this,CatererEditEvent.class);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_back.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
