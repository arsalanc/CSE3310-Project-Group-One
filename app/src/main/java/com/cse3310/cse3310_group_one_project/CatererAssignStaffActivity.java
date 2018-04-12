package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererAssignStaffActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_assign_staff);
        //TODO: Submit button functionality
        Button Assign_staff_submit=findViewById(R.id.assign_staff_submit);
        Button back_assign_staff=findViewById(R.id.back_assign_staff);
        back_assign_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignStaffBack();
            }
        });
    }

    public void assignStaffSubmit(){

    }
    public void assignStaffBack(){
        Intent intent_back = new Intent(this,CatererEditEventActivity.class);
        startActivity(intent_back);
    }
}
