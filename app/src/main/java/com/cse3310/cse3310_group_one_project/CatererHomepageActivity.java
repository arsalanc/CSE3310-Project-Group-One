package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererHomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_homepage);

        Button view_reserved_events=findViewById(R.id.reserved_events_caterer);
        Button view_requested_events=findViewById(R.id.requested_events_caterer);
        Button profile_management=findViewById(R.id.profile_management_caterer);
        Button logout=findViewById(R.id.logout_caterer);

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

    public void logout(){
        Intent intent_logout = new Intent(this,MainActivity.class);
        startActivity(intent_logout);
    }
    public void profile_management(){
        Intent intent_profileManagement = new Intent(this, ProfileManagementActivity.class);
        startActivity(intent_profileManagement);
    }
    public void view_reserved_events(){
        Intent intent_reserved_events = new Intent(this, CatererReservedEventsActivity.class);
        startActivity(intent_reserved_events);
    }
    public void view_requested_events(){
        Intent intent_requested_events = new Intent(this, CatererRequestedEventsActivity.class);
        startActivity(intent_requested_events);
    }
}
