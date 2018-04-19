package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererApproveEventActivity extends AppCompatActivity {
    Spinner hall;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_approve_event);
        //TODO: assign hall  to event and reserve event after submit button click
        Button back_button = (Button) findViewById(R.id.reserve_event_back);
        Button submit_button = (Button) findViewById(R.id.reserve_event_submit);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererApproveEventActivity.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Halls));
        hall = findViewById(R.id.select_hall);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hall.setAdapter(myAdapter);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });
    }

    public void backButton(){
        Intent intent_back = new Intent(this,CatererRequestedEventsActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
    public void submitButton(){

    }
}
