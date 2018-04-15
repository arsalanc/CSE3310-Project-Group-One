package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserHomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        Button request_event = (Button) findViewById(R.id.request_event_user);
        Button view_requested_events = (Button) findViewById(R.id.view_requested_events_user);
        Button view_reserved_events = (Button) findViewById(R.id.view_reserved_events_user);
        Button view_schedule = (Button) findViewById(R.id.view_schedule_user);
        Button profile_management = (Button) findViewById(R.id.profile_management_user);
        Button logout = (Button) findViewById(R.id.logout_user);

        request_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request_event();
            }
        });
        view_requested_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_requested_events();
            }
        });
        view_reserved_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_reserved_events();
            }
        });
        view_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_schedule();
            }
        });
        profile_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile_management();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    public void request_event(){
        Intent intent_requestEvent = new Intent(this,UserRequestEventActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_requestEvent.putExtra("USER", user);
        startActivity(intent_requestEvent);
    }
    public void view_requested_events(){
        Intent intent_viewRequests = new Intent(this,UserViewRequestedEvents.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_viewRequests.putExtra("USER", user);
        startActivity(intent_viewRequests);
    }
    public void view_reserved_events(){
        Intent intent_viewReserved = new Intent(this,UserReservedEventsActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_viewReserved.putExtra("USER", user);
        startActivity(intent_viewReserved);
    }
    public void view_schedule(){
        Intent intent_viewSchedule = new Intent(this,UserScheduleActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_viewSchedule.putExtra("USER", user);
        startActivity(intent_viewSchedule);
    }
    public void profile_management(){
        Intent intent_profileManagement = new Intent(this,ProfileManagementActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_profileManagement.putExtra("USER", user);
        startActivity(intent_profileManagement);
    }
    public void logout(){
        Intent intent_logout = new Intent(this,MainActivity.class);
        startActivity(intent_logout);
    }
}
