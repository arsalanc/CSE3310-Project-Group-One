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
import com.cse3310.cse3310_group_one_project.Models.Event;
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
    Spinner food_spinner,drink_spinner,ent_spinner;
    //TextView resource_amount;
    //Button btn_add, btn_sub;
    DBManager db;
    int amount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_add_resources);
        //TODO: submit button functionality
        Button add_resources_submit=findViewById(R.id.add_resources_submit_a);
        Button add_resources_back=findViewById(R.id.add_resources_back_a);
        food_spinner = findViewById(R.id.foodSpinner);
        drink_spinner = findViewById(R.id.drinkSpinner);
        ent_spinner = findViewById(R.id.entSpinner);
        db=new DBManager(this);
        //int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        Event e = (Event) getIntent().getSerializableExtra("EVENT");
        String mealType = e.getMeal_type();
        String drinkType = e.getDrink_venue();
        //FOOD
        if(mealType.equalsIgnoreCase("american")){
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_american));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapter);
        }
        else if(mealType.equalsIgnoreCase("chinese")){
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_chinese));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapter);
        }
        else if(mealType.equalsIgnoreCase("french")){
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_french));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapter);
        }
        else if(mealType.equalsIgnoreCase("greek")){
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_greek));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapter);
        }
        else if(mealType.equalsIgnoreCase("indian")){
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_indian));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapter);
        }
        else if(mealType.equalsIgnoreCase("italian")){
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_italian));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapter);
        }
        else if(mealType.equalsIgnoreCase("japanese")){
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_japanese));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapter);
        }
        else{
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_mexican));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapter);
        }
        //DRINK
        if(drinkType.equalsIgnoreCase("alcoholic")){
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.drinks_alcoholic));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            drink_spinner.setAdapter(myAdapter);
        }
        else{
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.drinks_standard));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            drink_spinner.setAdapter(myAdapter);
        }
        //ENT
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CatererAddResources.this,
                R.layout.spinner_item2,getResources().getStringArray(R.array.ent_items));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ent_spinner.setAdapter(myAdapter);
    }

    public void addResourcesSubmit(DBManager db){
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        String foodChoice = food_spinner.getSelectedItem().toString();
        String drinkChoice = drink_spinner.getSelectedItem().toString();
        String entChoice = ent_spinner.getSelectedItem().toString();
        int new_amount;
        if ((!foodChoice.equalsIgnoreCase("american food") || !foodChoice.equalsIgnoreCase("chinese food") || !foodChoice.equalsIgnoreCase("indian food") || !foodChoice.equalsIgnoreCase("italian food")
        || !foodChoice.equalsIgnoreCase("japanese food") || !foodChoice.equalsIgnoreCase("greek food") || !foodChoice.equalsIgnoreCase("french food") || !foodChoice.equalsIgnoreCase("mexican food"))
                && !drinkChoice.equalsIgnoreCase("Drink") && !entChoice.equalsIgnoreCase("Entertainment Item")){

            // ADD entries to the database

        }else{
            Toast.makeText(this, "A resource was not chosen.", Toast.LENGTH_SHORT).show();
        }

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
