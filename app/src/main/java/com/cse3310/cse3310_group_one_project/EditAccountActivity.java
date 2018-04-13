package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class EditAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

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
                submit();
            }
        });
    }

    public void cancel(){
        Intent intent_cancel = new Intent(this,ProfileManagementActivity.class);
        startActivity(intent_cancel);
    }
    public void submit(){
        //TODO: Change account details
        Intent intent_submit = new Intent(this,ProfileManagementActivity.class);
        startActivity(intent_submit);
    }
}
