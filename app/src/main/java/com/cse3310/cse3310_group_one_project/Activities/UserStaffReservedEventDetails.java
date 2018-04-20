package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018?.
 */

public class UserStaffReservedEventDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_staff_reserved_event_details);
        Button caterer_info = (Button) findViewById(R.id.caterer_information);
        Button back_button = (Button) findViewById(R.id.event_details_back_user);
        caterer_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caterer_info();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });
    }
    public void back_button(){
        //TODO: go back to homepage of staff or user based on account type
        finish();
    }
    public void caterer_info(){
        Intent intent_catererInfo = new Intent(this,ViewCatererInfo.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_catererInfo.putExtra("USER", user);
        startActivity(intent_catererInfo);
    }
}
