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

        Button remove_resources_submit=findViewById(R.id.remove_resources_submit);
        Button remove_resources_back=findViewById(R.id.remove_resources_back);
        btn_add = (Button)findViewById(R.id.btn_add_resource_r);
        btn_sub = (Button)findViewById(R.id.btn_sub_resources_r);
        resources = findViewById(R.id.resource_type);
        resource_amount=findViewById(R.id.resource_amount);
        List<Resource> resource=db.getCurrentResources(event_id);
        for(Resource r:resource)
        {
            String v=r.getResource_type();
            resource_list.add(v);
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererRemoveResources.this,
                R.layout.spinner_item,resource_list);
        resources.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        remove_resources_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeResourcesBack();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount++;
                resource_amount.setText("Amount: "+amount);
                resource_amount.setTextColor(Color.WHITE);
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount<=0)
                    Toast.makeText(CatererRemoveResources.this, "You can't do that", Toast.LENGTH_LONG).show();
                else {
                    amount--;
                    resource_amount.setText("Amount: " + amount);
                    resource_amount.setTextColor(Color.WHITE);
                }
            }
        });
        remove_resources_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeResourcesSubmit(db);
            }
        });
    }
    public void removeResourcesSubmit(DBManager db){
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        String name = resources.getSelectedItem().toString();
        int old_amount = db.retrieveAmountResources(event_id,name);
        int new_amount = old_amount-amount;
        db.updateResources(event_id,new_amount,name);
        Intent intent_back = new Intent(this,CatererEditEvent.class);
        intent_back.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("PREVIOUS_PAGE", (Class) getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
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
