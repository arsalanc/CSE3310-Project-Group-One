package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserRequestEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_event);

        Button cancel = (Button) findViewById(R.id.request_event_cancel);
        Button confirm = (Button) findViewById(R.id.request_event_confirm);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm();
            }
        });
    }

    public void cancel(){
        Intent intent_cancel = new Intent(this,UserHomepageActivity.class);
        startActivity(intent_cancel);
    }

    public void confirm(){
        //TODO: User entered details to create an event object and send to caterer as request
        Intent intent_requestEvent = new Intent(this,UserHomepageActivity.class);
        startActivity(intent_requestEvent);
    }
}
