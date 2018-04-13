package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererAproveEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_aprove_event);
        //TODO: assign hall  to event and reserve event after submit button click
        Button back_button = (Button) findViewById(R.id.reserve_event_back);
        Button submit_button = (Button) findViewById(R.id.reserve_event_submit);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });
    }

    public void backButton(){
        Intent intent_register = new Intent(this,CatererRequestedEventsActivity.class);
        startActivity(intent_register);
    }
    public void submitButton(){

    }
}
