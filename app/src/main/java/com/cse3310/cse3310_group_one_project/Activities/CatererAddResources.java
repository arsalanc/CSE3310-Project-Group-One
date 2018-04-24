package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
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

/**
 * Created by Arsalan on 4/11/2018.
 */

/**
 * Im adding a simple test comment here
 */
public class CatererAddResources extends AppCompatActivity {
    Spinner resource_type;
    TextView resource_amount;
    Button btn_add, btn_sub;
    DBManager db;
    int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_add_resources);
        //TODO: submit button functionality
        Button add_resources_submit=findViewById(R.id.add_resources_submit_a);
        Button add_resources_back=findViewById(R.id.add_resources_back_a);
        btn_add = (Button)findViewById(R.id.btn_add_resources_a);
        btn_sub = (Button)findViewById(R.id.btn_sub_resources_a);
        resource_amount = findViewById(R.id.resource_amount);
        resource_type=findViewById(R.id.resource_type);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                R.layout.spinner_item2,getResources().getStringArray(R.array.Resource_Types));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resource_type.setAdapter(myAdapter);
        db=new DBManager(this);
        add_resources_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResourcesBack();
            }
        });
        add_resources_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResourcesSubmit(db);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount++;
                resource_amount.setText("Amount: "+amount);
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount<=0)
                    Toast.makeText(CatererAddResources.this, "You cant do that", Toast.LENGTH_LONG).show();
                else {
                    amount--;
                    resource_amount.setText("Amount: " + amount);
                }
            }
        });
    }

    public void addResourcesSubmit(DBManager db){
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        String type = resource_type.getSelectedItem().toString();
        Resource r = new Resource(amount,type,event_id);

        db.addResources(r);
        Intent intent_back = new Intent(this,CatererEditEvent.class);
        intent_back.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("PREVIOUS_PAGE", (Class) getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
    public void addResourcesBack(){
        Intent intent_back = new Intent(this,CatererEditEvent.class);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_back.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("PREVIOUS_PAGE", (Class) getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
