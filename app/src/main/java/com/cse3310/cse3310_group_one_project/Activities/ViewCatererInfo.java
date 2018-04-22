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

/**
 * Created by Arsalan on 4/11/2018.
 */

public class ViewCatererInfo extends AppCompatActivity {
    TextView name,phone;
    DBManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_caterer_info);
        name=(TextView)findViewById(R.id.caterer_name);
        phone=(TextView)findViewById(R.id.caterer_phone_number);

        db=new DBManager(this);
        int event_id = (Integer) getIntent().getSerializableExtra("EVENT_ID");
        Event e = db.retrieveEvent(event_id);
        int caterer_id=e.getCaterer_id();
        User caterer=db.retrieveUserById(caterer_id);
        String caterer_name=caterer.getFname() + " " + caterer.getLname();
        String phone_number = caterer.getPhoneNumber();
        name.setText(name.getText().toString()+ " "+caterer_name);
        phone.setText(phone.getText().toString()+ " "+phone_number);

        Button back_button = (Button) findViewById(R.id.caterer_info_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back_button();
            }
        });

    }
    public void back_button(){
        Intent intent_back = new Intent(this, UserStaffReservedEventDetails.class);
        int event_id = (int) getIntent().getSerializableExtra("EVENT_ID");
        intent_back.putExtra("EVENT_ID",event_id);
        User user = (User) getIntent().getSerializableExtra("USER");
        intent_back.putExtra("PREVIOUS_PAGE", getIntent().getSerializableExtra("PREVIOUS_PAGE"));
        intent_back.putExtra("USER", user);
        startActivity(intent_back);
    }
}
