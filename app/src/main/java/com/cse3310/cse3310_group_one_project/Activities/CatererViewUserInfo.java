package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererViewUserInfo extends AppCompatActivity {
    TextView user_name, user_number;
    DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_view_user_info);
        int event_id = (Integer) getIntent().getSerializableExtra("EVENT_ID");
        db=new DBManager(this);
        Event e=db.retrieveEvent(event_id);
        int user_id = e.getOwner_id();
        User event_user=db.retrieveUserById(user_id);
        String name = event_user.getFname() + " " + event_user.getLname();
        String phone = event_user.getPhoneNumber();

        user_name=(TextView) findViewById(R.id.user_info_name);
        user_number=(TextView) findViewById(R.id.user_info_number);
        user_name.setText(user_name.getText().toString() + " " + name);
        user_number.setText(user_number.getText().toString() + " " + phone);
        Button User_info_back = (Button) findViewById(R.id.user_info_back);
        User_info_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                userInfoBack();
            }
        });
    }

    public void userInfoBack(){
        Intent intent_back = new Intent(this, CatererReservedEvents.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
