package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button cancel_button = (Button) findViewById(R.id.cancel_register);
        Button submit_button = (Button) findViewById(R.id.register_submit);
        //TODO: Create account using edit text data when submit button is clicked
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelRegister();
            }
        });
    }

    public void cancelRegister(){
        Intent cancel_register = new Intent(this,MainActivity.class);
        startActivity(cancel_register);
    }

    public void submitRegister(){

    }
}

