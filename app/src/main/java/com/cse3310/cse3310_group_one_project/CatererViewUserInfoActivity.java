package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererViewUserInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_view_user_info);
        Button User_info_back = (Button) findViewById(R.id.user_info_back);
        User_info_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                userInfoBack();
            }
        });
    }

    public void userInfoBack(){
        Intent intent_back = new Intent(this,CatererEditEventActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
