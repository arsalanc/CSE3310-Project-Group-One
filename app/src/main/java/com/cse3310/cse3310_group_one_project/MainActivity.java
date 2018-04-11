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
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Register();
            }
        });
    }
    public void open_Register(){
        Intent intent_register = new Intent(this,RegisterActivity.class);
        startActivity(intent_register);
    }
}
