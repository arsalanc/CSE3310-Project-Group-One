package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button register_button = (Button) findViewById(R.id.Register);
        Button login_button = (Button) findViewById(R.id.login);
        //TODO: connect to database login using information stored in database
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Register();
            }
        });
        //testing purposes
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                login();
            }
        });
    }
    public void open_Register(){
        Intent intent_register = new Intent(this,RegisterActivity.class);
        startActivity(intent_register);
    }
    //Testing purposes
    public void login(){
        Intent intent_login = new Intent(this,CatererHomepageActivity.class);
        startActivity(intent_login);
    }

}
