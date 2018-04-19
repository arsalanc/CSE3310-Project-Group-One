package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;

/**
 * Created by Arsalan on 4/11/2018.
 */

/**
 * Im adding a simple test comment here
 */
public class CatererAddResources extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_add_resources);
        //TODO: submit button functionality
        Button add_resources_submit=findViewById(R.id.add_resources_submit);
        Button add_resources_back=findViewById(R.id.add_resources_back);
        add_resources_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResourcesBack();
            }
        });
    }

    public void addResourcesSubmit(){

    }
    public void addResourcesBack(){
        Intent intent_back = new Intent(this,CatererEditEvent.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
