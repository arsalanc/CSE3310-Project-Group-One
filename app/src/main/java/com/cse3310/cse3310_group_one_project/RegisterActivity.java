package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText fname,lname,username,password,phone_number;
    Spinner account_type;
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
        account_type =(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                R.layout.spinner_item,getResources().getStringArray(R.array.Account_Types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        account_type.setAdapter(myAdapter);
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
        boolean readyToSubmit = true;
        String acct_type_temp,fname_temp,lname_temp,username_temp,
                password_temp,phone_number_temp;
        fname_temp=fname.getText().toString();
        lname_temp=lname.getText().toString();
        username_temp=username.getText().toString();
        password_temp = password.getText().toString();
        phone_number_temp = phone_number.getText().toString();
        acct_type_temp = account_type.getSelectedItem().toString().toLowerCase();

        if ((fname_temp != null && !fname_temp.isEmpty()) && (lname_temp != null && !lname_temp.isEmpty()) && (username_temp != null && !username_temp.isEmpty())
                && (password_temp != null && !password_temp.isEmpty()) && (phone_number_temp != null && !phone_number_temp.isEmpty())) {
            user.setFname(fname_temp);
            user.setLname(lname_temp);
            user.setUsername(username_temp);
            user.setPassword(password_temp);
            if( phone_number_temp.length() == 10) {
                user.setPhoneNumber(phone_number_temp);
            }
            else{
                readyToSubmit = false;
                Toast.makeText(this, "10-Digit Phone Number Required", Toast.LENGTH_LONG).show();
            }
            if(acct_type_temp.equalsIgnoreCase("caterer") || acct_type_temp.equalsIgnoreCase("user") || acct_type_temp.equalsIgnoreCase("staff"))
            {
                user.setAccountType(acct_type_temp);
            }
            else {
                readyToSubmit = false;
                Toast.makeText(this, "Please choose an Account Type", Toast.LENGTH_LONG).show();
            }
        }
        else{
            readyToSubmit = false;
            Toast.makeText(this, "Empty Text Field(s)", Toast.LENGTH_LONG).show();
        }

        if(readyToSubmit){
            db.addNewUser(user);
            Intent submit_register = new Intent(this, MainActivity.class);
            startActivity(submit_register);
        }
    }
}

