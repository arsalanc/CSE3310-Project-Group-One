package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Resource;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererViewResources extends AppCompatActivity {
    DBManager db;
    TextView food,drink,ent;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_view_resources);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        db=new DBManager(this);
        food = findViewById(R.id.foodTextView);
        drink=findViewById(R.id.drinksTextView);
        ent=findViewById(R.id.entTextView);

        Resource resource=db.retrieveResources(event_id);
        food.setText(food.getText().toString() + " "+resource.getResource_food());
        drink.setText(drink.getText().toString() + " "+ resource.getResource_drink());
        ent.setText(ent.getText().toString() + " "+ resource.getResource_ent());
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
