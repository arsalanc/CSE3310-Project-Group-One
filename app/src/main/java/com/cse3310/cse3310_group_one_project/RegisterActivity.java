package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText fname,lname,username,password,account_type,phone_number;
    DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBManager(this);
        //db.recreateTable();
        Button cancel_button = (Button) findViewById(R.id.cancel_register);
        Button submit_button = (Button) findViewById(R.id.register_submit);
        fname=(EditText) findViewById(R.id.Firstname);
        lname=(EditText) findViewById(R.id.Lastname);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        account_type=(EditText) findViewById(R.id.account_type);
        phone_number=(EditText) findViewById(R.id.phone_number);
        //TODO: Create account using edit text data when submit button is clicked
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelRegister();
            }
        });
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRegister(db);
            }
        });
    }

    public void cancelRegister(){
        Intent cancel_register = new Intent(this,MainActivity.class);
        startActivity(cancel_register);
    }

    public void submitRegister(DBManager db){
        UserModel user =new UserModel();
        user.setFname(fname.getText().toString());
        user.setLname(lname.getText().toString());
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());
        user.setPhoneNumber(phone_number.getText().toString());
        String acct_type = account_type.getText().toString().toLowerCase();
        if(acct_type.equalsIgnoreCase("caterer")||acct_type.equalsIgnoreCase("user")||acct_type.equalsIgnoreCase("staff"))
        {
            user.setAccountType(acct_type);
            db.addNewUser(user);
            Intent submit_register = new Intent(this, MainActivity.class);
            startActivity(submit_register);

        }
        else {
            Toast.makeText(this, "Change account type to caterer,staff or user", Toast.LENGTH_LONG).show();
        }
    }
}

