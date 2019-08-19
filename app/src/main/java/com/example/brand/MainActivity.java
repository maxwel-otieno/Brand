package com.example.brand;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login, signup, forgotpass;
    EditText username, password;
    RelativeLayout rellay_1, rellay_2;
    SQLiteDatabase db;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay_1.setVisibility(View.VISIBLE);
            rellay_2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellay_1 = (RelativeLayout) findViewById(R.id.rellay_1);
        rellay_2 = (RelativeLayout) findViewById(R.id.rellay_2);

        handler.postDelayed(runnable, 3000);

        login = (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.edt_username);
        password = (EditText) findViewById(R.id.edt_password);
        forgotpass = (Button) findViewById(R.id.btn_forgotpass);

        //create a database if exists.
        // first we eed to import the SQLite database
        db = openOrCreateDatabase("carrental", MODE_PRIVATE, null);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty()){
                    messages("USERNAME ERROR", "Please fill in your username");
                }else if(password.getText().toString().isEmpty()){
                    messages("PASSWORD ERROR", "Enter your password to continue");
                }else{
                    Intent go = new Intent(MainActivity.this, loginPage.class);
                    go.putExtra("myName", username.getText().toString());

                    // Use a cursor to query and select data from your db table
                    Cursor cursor = db.rawQuery("SELECT * FROM customers", null);

                    startActivity(go);
                }
            }
        });

        signup = (Button) findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent enda = new Intent(getBaseContext(), Signup.class);
                startActivity(enda);
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(MainActivity.this, forgotPassword.class);
                startActivity(go);
            }
        });
    }

    //message display function
    public void messages(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.create().show();
    }
}
