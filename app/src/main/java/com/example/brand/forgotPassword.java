package com.example.brand;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotPassword extends AppCompatActivity{

    EditText forgotPassLast, forgotPassNew, forgotPassNewConf;
    Button btnForgotPassLogIn, btnForgotPassSign, btnConfirm;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPassLast = (EditText) findViewById(R.id.edt_forgotpass_last_password);
        forgotPassNew = (EditText) findViewById(R.id.edt_forgotpass_new_password);
        forgotPassNewConf = (EditText) findViewById(R.id.edt_forgotpass_new_password_conf);

        btnForgotPassLogIn = (Button) findViewById(R.id.btn_forgotpass_logIn);
        btnForgotPassSign = (Button) findViewById(R.id.btn_forgotpass_signIn);
        btnConfirm = (Button) findViewById(R.id.btn_forgotpass_confirm);

        db = openOrCreateDatabase("carrental", MODE_PRIVATE, null);

        btnForgotPassLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(getBaseContext(), MainActivity.class);
                startActivity(go);
            }
        });

        btnForgotPassSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(getBaseContext(), Signup.class);
                startActivity(go);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(forgotPassword.this, "New Password sent to your email", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
