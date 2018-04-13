package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class StaffHomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_homepage);

        Button view_assigned_events = (Button) findViewById(R.id.view_assigned_events);
        Button profile_management = (Button) findViewById(R.id.profile_management_staff);
        Button logout = (Button) findViewById(R.id.logout_staff);

        view_assigned_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAssignedEvents();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        profile_management.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                profile_management();
            }
        });
    }

    public void viewAssignedEvents(){
        Intent intent_assignedEvents = new Intent(this,StaffAssignedEventsActivity.class);
        startActivity(intent_assignedEvents);
    }
    public void logout(){
        Intent intent_logout = new Intent(this,MainActivity.class);
        startActivity(intent_logout);
    }
    public void profile_management(){
        Intent intent_profileManagement = new Intent(this, ProfileManagementActivity.class);
        startActivity(intent_profileManagement);
    }
}
