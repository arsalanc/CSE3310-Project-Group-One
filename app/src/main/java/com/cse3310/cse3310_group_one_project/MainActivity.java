package com.cse3310.cse3310_group_one_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Button register_button = (Button) findViewById(R.id.Register);
        Button login_button = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username_input);
        password = (EditText) findViewById(R.id.password_input);
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
        Intent intent_register = new Intent(this,RegisterActivity.class);
        startActivity(intent_register);
    }
    //Testing purposes
    public void login(DBManager db){
        //DBManager handler = new DBManager(this);
        UserModel user = db.retrieveUser(username.getText().toString(), password.getText().toString());
        Intent intent = new Intent(this, CatererHomepageActivity.class);
        if (user != null) {
            if(user.getAccountType().equalsIgnoreCase("caterer")) {
                intent = new Intent(this, CatererHomepageActivity.class);
                intent.putExtra("USER", user);
            }
            else if(user.getAccountType().equalsIgnoreCase("user")){
                intent = new Intent(this, UserHomepageActivity.class);
                intent.putExtra("USER", user);
            }
            else if(user.getAccountType().equalsIgnoreCase("staff")) {
                intent = new Intent(this, StaffHomepageActivity.class);
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

}
