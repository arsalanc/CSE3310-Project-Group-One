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
        Button back_event=findViewById(R.id.reserved_events_back);
        //TODO: view_details function

        Edit_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEvent();
            }
        });
        back_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backEvent();
            }
        });


    }

    public void view_details(){

    }
    public void editEvent(){
        Intent intent_editEvent = new Intent(this,CatererEditEventActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_editEvent.putExtra("USER", user);
        startActivity(intent_editEvent);
    }
    public void backEvent(){
        Intent intent_backEvent = new Intent(this,CatererHomepageActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_backEvent.putExtra("USER", user);
        startActivity(intent_backEvent);
    }
}
