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

public class CatererRemoveResources extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_remove_resources);

        Button remove_resources_submit=findViewById(R.id.remove_resources_submit);
        Button remove_resources_back=findViewById(R.id.remove_resources_back);
        remove_resources_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeResourcesBack();
            }
        });
    }

    public void removeResourcesSubmit(){

    }

    public void removeResourcesBack(){
        Intent intent_removeResourcesBack = new Intent(this,CatererEditEvent.class);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_removeResourcesBack.putExtra("USER", user);
        startActivity(intent_removeResourcesBack);
    }
}
