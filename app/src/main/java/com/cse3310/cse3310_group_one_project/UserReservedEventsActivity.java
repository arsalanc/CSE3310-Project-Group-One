package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserReservedEventsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reserved_events);

        Button back_button = (Button) findViewById(R.id.user_reserved_events_back);
        Button view_details = (Button) findViewById(R.id.view_details_reserved_user);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });
        view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_details();
            }
        });
    }

    public void back_button(){
        Intent intent_back = new Intent(this,UserHomepageActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
    public void view_details(){
        Intent intent_viewDetails = new Intent(this,UserStaffReservedEventDetails.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_viewDetails.putExtra("USER", user);
        startActivity(intent_viewDetails);
    }
}
