package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class ProfileManagementActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        Button edit_account=findViewById(R.id.edit_account);
        Button delete_account=findViewById(R.id.delete_account);
        Button cancel=findViewById(R.id.cancel_PM);
        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount("","");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        edit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAccount();
            }
        });
    }
    public void deleteAccount(String Username, String Password){
        //TODO:delete account
        Intent intent_redirect = new Intent(this, MainActivity.class);
        startActivity(intent_redirect);
    }

    public void editAccount(){
        Intent intent_editAccount = new Intent(this,EditAccountActivity.class);
        startActivity(intent_editAccount);
    }

    public void cancel(){
        //TODO: check account type of current user and redirect to homepage
        finish(); //temporary
    }
}
