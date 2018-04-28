package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererRemoveStaff extends AppCompatActivity {
    Spinner remove_staff;
    DBManager db;
    List<String> staffNames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_remove_staff);
        //TODO: Submit button functionality
        Button remove_staff_submit=findViewById(R.id.remove_staff_submit);
        Button remove_staff_back=findViewById(R.id.remove_staff_back);

        remove_staff = findViewById(R.id.remove_staff_spinner);
        int event_id =  (int) getIntent().getSerializableExtra("EVENT_ID");
        db = new DBManager(this);
        staffNames=db.currentStaff(event_id);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererRemoveStaff.this,
                R.layout.spinner_item,staffNames);
        remove_staff.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        remove_staff_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeStaffBack();
            }
        });
        remove_staff_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeStaffSubmit(db);
            }
        });
    }

    public void removeStaffSubmit(DBManager db){
        String selected=remove_staff.getSelectedItem().toString();
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        String[] name = selected.split("\\s+");
        int staff_id=db.retrieveUserID(name[0],name[1]);
        db.removeStaff(event_id,staff_id);
        Intent intent_remove = new Intent(this,CatererEditEvent.class);
        intent_remove.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_remove.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_remove.putExtra("USER", user);
        startActivity(intent_remove);
    }

    public void removeStaffBack(){
        Intent intent_back = new Intent(this,CatererEditEvent.class);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_back.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
