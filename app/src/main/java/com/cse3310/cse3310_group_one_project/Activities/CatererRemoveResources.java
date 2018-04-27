package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Resource;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererRemoveResources extends AppCompatActivity {
    DBManager db;
    Spinner resources;
    List<String> resource_list=new ArrayList<>();
    Button btn_add, btn_sub;
    int amount;
    TextView resource_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_remove_resources);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        db=new DBManager(this);

        Button view_resources_back=findViewById(R.id.view_resources_back);
       // resources = findViewById(R.id.resource_type);
        //resource_amount=findViewById(R.id.resource_amount);

        view_resources_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeResourcesBack();
            }
        });

    }

    public void removeResourcesBack(){
        Intent intent_back = new Intent(this,CatererEditEvent.class);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_back.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("PREVIOUS_PAGE", (Class) getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
