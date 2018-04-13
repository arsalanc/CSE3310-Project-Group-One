package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererRequestedEventsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_requested_events);
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
    }

    public void viewDetails(){

    }
    public void reserve(){
        Intent intent_reserve = new Intent(this,CatererAproveEventActivity.class);
        startActivity(intent_reserve);
    }
    public void deny(){

    }
    public void requested_back(){
        Intent intent_requestBack = new Intent(this,CatererHomepageActivity.class);
        startActivity(intent_requestBack);
    }
}
