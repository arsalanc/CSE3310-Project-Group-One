package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arsalan on 4/11/2018.
 */

public class CatererRemoveResourcesActivity extends AppCompatActivity {
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
        Intent intent_removeResourcesBack = new Intent(this,CatererEditEventActivity.class);
        UserModel user = (UserModel) getIntent().getSerializableExtra("USER");
        intent_removeResourcesBack.putExtra("USER", user);
        startActivity(intent_removeResourcesBack);
    }
}
