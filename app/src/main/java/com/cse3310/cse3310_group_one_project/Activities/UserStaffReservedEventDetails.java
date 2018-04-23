package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.Models.Event;
import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.R;
import java.lang.String;
/**
 * Created by Arsalan on 4/11/2018?.
 */

public class UserStaffReservedEventDetails extends AppCompatActivity {
    TextView party_size,date,time,duration,meal_type,venue_type,formality,drink,hall,total_cost;
    DBManager db;
    double cost = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_staff_reserved_event_details);

        party_size = (TextView) findViewById(R.id.reserved_event_party_size);
        date = (TextView) findViewById(R.id.reserved_event_date);
        time = (TextView) findViewById(R.id.reserved_event_time);
        duration = (TextView) findViewById(R.id.reserved_event_duration);
        meal_type = (TextView) findViewById(R.id.reserved_event_Meal);
        venue_type = (TextView) findViewById(R.id.reserved_event_venue);
        formality = (TextView) findViewById(R.id.reserved_event_formality);
        drink = (TextView) findViewById(R.id.reserved_event_drink);
        hall = (TextView) findViewById(R.id.reserved_event_hall);
        total_cost = (TextView) findViewById(R.id.total_cost);
        db = new DBManager(this);
        set_text(db);

        Button caterer_info = (Button) findViewById(R.id.caterer_information);
        Button back_button = (Button) findViewById(R.id.event_details_back_user);
        User user = (User) getIntent().getSerializableExtra("USER");
        if(user.getAccountType().equalsIgnoreCase("caterer"))
        {
            caterer_info.setText("User info");
        }
        caterer_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caterer_info();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });
    }
    public void back_button(){
        //TODO: go back to homepage of staff or user based on account type

        User user = (User) getIntent().getSerializableExtra("USER");
        if(user.getAccountType().equalsIgnoreCase("caterer"))
        {
            Intent intent_back = new Intent(this,CatererReservedEvents.class);
            intent_back.putExtra("USER", user);
            startActivity(intent_back);
        }
        else if(user.getAccountType().equalsIgnoreCase("staff"))
        {
            Intent intent_back = new Intent(this,StaffAssignedEvents.class);
            intent_back.putExtra("USER", user);
            startActivity(intent_back);
        }
        else if(user.getAccountType().equalsIgnoreCase("user"))
        {
            Intent intent_back = new Intent(this,UserHomepage.class);
            intent_back.putExtra("USER", user);
            startActivity(intent_back);
        }
        finish();
    }
    public void caterer_info(){

        Intent intent;
        User user = (User) getIntent().getSerializableExtra("USER");
        if(user.getAccountType().equalsIgnoreCase("caterer"))
        {
            intent = new Intent(this, CatererViewUserInfo.class);
        }
        else {
            intent = new Intent(this, ViewCatererInfo.class);
        }
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent.putExtra("EVENT_ID",event_id);
        intent.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent.putExtra("USER", user);
        startActivity(intent);
    }
    public void set_text(DBManager db){
        String partySize,mealType,drinkType,formalityType;
        double personsCount = 0;
        int event_id = (Integer) getIntent().getSerializableExtra("EVENT_ID");
        Event e = db.retrieveEvent(event_id);
        party_size.setText(party_size.getText().toString() + " " + e.getParty_size());
        date.setText(date.getText().toString() + " " + e.getDate());
        time.setText(time.getText().toString() + " " + e.getTime());
        duration.setText(duration.getText().toString() + " " + e.getDuration() + " Hours");
        meal_type.setText(meal_type.getText().toString() + " " + e.getMeal_type());
        venue_type.setText(venue_type.getText().toString() + " " + e.getMeal_venue());
        formality.setText(formality.getText().toString() + " " + e.getFormality());
        drink.setText(drink.getText().toString() + " " + e.getDrink_venue());
        hall.setText(hall.getText().toString() + " " + e.getHall());
        personsCount = e.getParty_size();
        if(e.getMeal_type().equalsIgnoreCase("breakfast")){
            cost += 8 * personsCount;
        }
        else if(e.getMeal_type().equalsIgnoreCase("lunch")){
            cost += 12 * personsCount;
        }
        else{
            cost += 18 * personsCount;
        }
        if (e.getFormality().equalsIgnoreCase("formal")) {
            cost = cost * 1.5;
        }
        if (e.getDrink_venue().equalsIgnoreCase("alcoholic")){
            cost += 15 * personsCount;
        }
        total_cost.setText(total_cost.getText().toString() + " $" + String.format("%.2f", cost));
    }
}
