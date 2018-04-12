package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererReservedEventsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_reserved_events);

        Button view_details=findViewById(R.id.reserved_events_details_caterer);
        Button Edit_event=findViewById(R.id.edit_event_caterer);
        //TODO: view_details function

        Edit_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_event();
            }
        });


    }

    public void view_details(){

    }
    public void edit_event(){
        Intent intent_editEvent = new Intent(this,CatererEditEventActivity.class);
        startActivity(intent_editEvent);
    }
}
