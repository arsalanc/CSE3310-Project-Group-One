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
    DBManager db;
    int amount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_add_resources);
        //TODO: submit button functionality
        Button set_resources_submit=findViewById(R.id.set_resources_submit);
        Button set_resources_back=findViewById(R.id.set_resources_back);
        food_spinner = findViewById(R.id.foodSpinner);
        drink_spinner = findViewById(R.id.drinkSpinner);
        ent_spinner = findViewById(R.id.entSpinner);
        db=new DBManager(this);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        //i think this might be the source of the bug (line 47)
        //could it be the if/if else structure? - miguel
        Event e =  db.retrieveEvent(event_id);
        String mealType = e.getMeal_venue();
        String drinkType = e.getDrink_venue();


        //FOOD
        if(mealType.equalsIgnoreCase("american")){
            ArrayAdapter<String> myAdapterf = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_american));
            myAdapterf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapterf);
        }
        else if(mealType.equalsIgnoreCase("chinese")){
            ArrayAdapter<String> myAdapterf = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_chinese));
            myAdapterf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapterf);
        }
        else if(mealType.equalsIgnoreCase("french")){
            ArrayAdapter<String> myAdapterf = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_french));
            myAdapterf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapterf);
        }
        else if(mealType.equalsIgnoreCase("greek")){
            ArrayAdapter<String> myAdapterf = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_greek));
            myAdapterf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapterf);
        }
        else if(mealType.equalsIgnoreCase("indian")){
            ArrayAdapter<String> myAdapterf = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_indian));
            myAdapterf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapterf);
        }
        else if(mealType.equalsIgnoreCase("italian")){
            ArrayAdapter<String> myAdapterf = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_italian));
            myAdapterf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapterf);
        }
        else if(mealType.equalsIgnoreCase("japanese")){
            ArrayAdapter<String> myAdapterf = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_japanese));
            myAdapterf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapterf);
        }
        else{
            ArrayAdapter<String> myAdapterf = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.food_mexican));
            myAdapterf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_spinner.setAdapter(myAdapterf);
        }
        //DRINK
        if(drinkType.equalsIgnoreCase("alcoholic")){
            ArrayAdapter<String> myAdapterd = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.drinks_alcoholic));
            myAdapterd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            drink_spinner.setAdapter(myAdapterd);
        }
        else{
            ArrayAdapter<String> myAdapterd = new ArrayAdapter<String>(CatererAddResources.this,
                    R.layout.spinner_item2,getResources().getStringArray(R.array.drinks_standard));
            myAdapterd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            drink_spinner.setAdapter(myAdapterd);
        }
        //ENT
        ArrayAdapter<String> myAdaptere = new ArrayAdapter<String>(CatererAddResources.this,
                R.layout.spinner_item2,getResources().getStringArray(R.array.ent_items));
        myAdaptere.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ent_spinner.setAdapter(myAdaptere);

        set_resources_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResourcesSubmit(db);
            }
        });
        set_resources_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResourcesBack();
            }
        });
    }

    public void setResourcesSubmit(DBManager db){
        boolean errorFlag = false;
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        String foodChoice = food_spinner.getSelectedItem().toString();
        String drinkChoice = drink_spinner.getSelectedItem().toString();
        String entChoice = ent_spinner.getSelectedItem().toString();
        int new_amount;

        if (foodChoice.equalsIgnoreCase("american food") || foodChoice.equalsIgnoreCase("chinese food") || foodChoice.equalsIgnoreCase("indian food") || foodChoice.equalsIgnoreCase("italian food")
                || foodChoice.equalsIgnoreCase("japanese food") || foodChoice.equalsIgnoreCase("greek food") || foodChoice.equalsIgnoreCase("french food") || foodChoice.equalsIgnoreCase("mexican food")){
            errorFlag = true;
        }
        if(drinkChoice.equalsIgnoreCase("Non-Alcoholic Drinks") || drinkChoice.equalsIgnoreCase("Alcoholic Drinks")){
            errorFlag = true;
        }
        if (entChoice.equalsIgnoreCase("Entertainment Items")){
            errorFlag = true;
        }
        if (errorFlag == false){

            if (db.retrieveResources(event_id) == null){
                Resource r = new Resource(foodChoice,drinkChoice,entChoice,event_id);
                db.addResources(r);
                Intent intent_back = new Intent(this,CatererEditEvent.class);
                intent_back.putExtra("EVENT_ID",event_id);
                User user = (User) getIntent().getSerializableExtra("USER");
                intent_back.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
                intent_back.putExtra("USER", user);
                startActivity(intent_back);
            }
            else{
                db.updateResources(event_id,foodChoice,drinkChoice,entChoice);
                Intent intent_back = new Intent(this,CatererEditEvent.class);
                intent_back.putExtra("EVENT_ID",event_id);
                User user = (User) getIntent().getSerializableExtra("USER");
                intent_back.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
                intent_back.putExtra("USER", user);
                startActivity(intent_back);
            }

        }
        else {
            Toast.makeText(this, "A resource was not selected", Toast.LENGTH_SHORT).show();
        }
    }
    public void setResourcesBack(){
        Intent intent_back = new Intent(this,CatererEditEvent.class);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_back.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
