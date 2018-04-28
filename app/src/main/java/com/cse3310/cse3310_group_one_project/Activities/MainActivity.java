package com.cse3310.cse3310_group_one_project.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cse3310.cse3310_group_one_project.Models.User;
import com.cse3310.cse3310_group_one_project.Models.DBManager;
import com.cse3310.cse3310_group_one_project.R;

//hello
public class MainActivity extends AppCompatActivity {

    DBManager db;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBManager(this);
        //db.recreateTable();
        Button register_button = findViewById(R.id.Register);
        Button login_button = findViewById(R.id.login);
        username = findViewById(R.id.username_input);
        password = findViewById(R.id.password_input);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
        //testing purposes
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                login(db);
            }
        });
    }
    public void openRegister(){
        Intent intent_register = new Intent(this,Register.class);
        startActivity(intent_register);
    }
    //Testing purposes
    public void login(DBManager db){
        //DBManager handler = new DBManager(this);
        User user = db.retrieveUser(username.getText().toString(), password.getText().toString());
        Intent intent = new Intent(this, CatererHomepage.class);
        if (user != null) {
            if(user.getAccountType().equalsIgnoreCase("caterer")) {
                intent = new Intent(this, CatererHomepage.class);
                intent.putExtra("USER", user);
            }
            else if(user.getAccountType().equalsIgnoreCase("user")){
                intent = new Intent(this, UserHomepage.class);
                intent.putExtra("USER", user);
            }
            else if(user.getAccountType().equalsIgnoreCase("staff")) {
                intent = new Intent(this, StaffHomepage.class);
                intent.putExtra("USER", user);
            }
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "WRONG EMAIL ID OR PASSWORD", Toast.LENGTH_LONG).show();
        }
        //Intent intent_login = new Intent(this,CatererHomepageActivity.class);
        //startActivity(intent_login);
    }
    private String someVariable;

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }

}
