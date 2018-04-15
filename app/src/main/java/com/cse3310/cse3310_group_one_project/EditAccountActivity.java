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

public class EditAccountActivity extends AppCompatActivity {
    EditText current_pass, new_pass, new_phone;
    DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        db = new DBManager(this);

        current_pass = (EditText) findViewById(R.id.current_pass);
        new_pass = (EditText) findViewById(R.id.new_pass);
        new_phone = (EditText) findViewById(R.id.new_pass);
        Button cancel = (Button) findViewById(R.id.edit_account_cancel);
        Button submit = (Button) findViewById(R.id.edit_account_submit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(db);
            }
        });
    }

    public void cancel(){
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        Intent intent_cancel = new Intent(this,ProfileManagementActivity.class);
        intent_cancel.putExtra("USER", user);
        startActivity(intent_cancel);
    }
    public void submit(DBManager db){
        //TODO: Change account details
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        String cur_pass =current_pass.getText().toString();
        if(!user.getPassword().equals(cur_pass))
        {
            Toast.makeText(this, "Incorrect current password", Toast.LENGTH_LONG).show();
        }
        else
        {
            //set new password if they enter something
            if(new_pass.getText().toString().length()>0)
                user.setPassword(new_pass.getText().toString());
            //change phone number only when they enter a 10 digit number
            if(new_phone.getText().toString().length()==10)
            {
                user.setPhoneNumber(new_phone.getText().toString());
            }
            int id = user.getId();
            db.deleteUser(id);
            db.addNewUser(user);
            Intent intent_submit = new Intent(this,ProfileManagementActivity.class);
            intent_submit.putExtra("USER", user);
            startActivity(intent_submit);
        }
        //Intent intent_submit = new Intent(this,ProfileManagementActivity.class);
        //startActivity(intent_submit);
    }
}
