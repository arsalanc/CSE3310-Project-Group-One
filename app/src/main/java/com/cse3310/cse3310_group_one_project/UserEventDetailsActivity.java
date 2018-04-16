package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserEventDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        setContentView(R.layout.activity_user_event_details);

        //TODO: display event information, cancel event on cancel button click
        Button back_button = (Button) findViewById(R.id.event_details_back_user);
        Button cancel_event = (Button) findViewById(R.id.cancel_event_user);
        super.onCreate(savedInstanceState);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });

       cancel_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel_event();
            }
        });
    }

    public void back_button(){
        Intent intent_back = new Intent(this,UserViewRequestedEvents.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
    public void cancel_event(){

    }
}
