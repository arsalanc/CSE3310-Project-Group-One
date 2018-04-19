package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class UserSchedule extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule);

        Button back_button = (Button) findViewById(R.id.user_schedule_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });
    }

    public void back_button(){
        Intent intent_back = new Intent(this,UserHomepage.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
