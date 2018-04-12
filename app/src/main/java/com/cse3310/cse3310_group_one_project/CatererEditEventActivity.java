package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererEditEventActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_edit_event);

        Button Assign_staff=findViewById(R.id.assign_staff);
        Button Remove_staff=findViewById(R.id.remove_staff);
        Button Add_resources=findViewById(R.id.add_resources);
        Button View_user_info=findViewById(R.id.view_user_info);
        Button Remove_resources=findViewById(R.id.remove_resources);
        Button Remove_event=findViewById(R.id.remove_event);
        Button Back=findViewById(R.id.back_caterer);
        Assign_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignStaff();
            }
        });

        Remove_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeStaff();
            }
        });
        Add_resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResources();
            }
        });
        View_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewUserInfo();
            }
        });
        Remove_resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeResources();
            }
        });
        Remove_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeEvent();
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prev_page();
            }
        });

    }
    public void assignStaff(){
        Intent intent_assignStaff = new Intent(this,CatererAssignStaffActivity.class);
        startActivity(intent_assignStaff);
    }
    public void removeStaff(){
        Intent intent_removeStaff = new Intent(this,CatererRemoveStaffActivity.class);
        startActivity(intent_removeStaff);
    }
    public void addResources(){
        Intent intent_addResources = new Intent(this,CatererAddResourcesActivity.class);
        startActivity(intent_addResources);
    }
    public void viewUserInfo(){
        Intent intent_viewUserInfo = new Intent(this,CatererViewUserInfoActivity.class);
        startActivity(intent_viewUserInfo);
    }
    public void removeResources(){
        Intent intent_removeResources = new Intent(this,CatererRemoveResourcesActivity.class);
        startActivity(intent_removeResources);
    }
    public void removeEvent(){
        //TODO: remove event
    }
    public void prev_page(){
        Intent intent_prev = new Intent(this,CatererReservedEventsActivity.class);
        startActivity(intent_prev);
    }

}
