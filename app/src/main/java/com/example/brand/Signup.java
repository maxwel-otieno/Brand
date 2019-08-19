package com.example.brand;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Signup extends AppCompatActivity {

    Button forgot, back_login, signup;
    EditText username, email, phone_number, password, confirm_password;
    RadioButton agree;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        forgot = (Button) findViewById(R.id.btn_exitApp);
        back_login = (Button) findViewById(R.id.btn_back_login);
        signup = (Button) findViewById(R.id.continue_sign);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent go = new Intent(Signup.this, forgotPassword.class);
//                startActivity(go);

//                builder.setTitle("Exit App");
//                builder.setMessage("Are you sure you want to exit the app");
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        System.exit(0);
//                    }
//                });
//                builder.create().show();

                System.exit(0);
//                // Use a cursor to query and select data from your db table
//                Cursor cursor = db.rawQuery("SELECT * FROM customers", null);
//
//                //Check if the cursor found any data
//                if(cursor.getCount() == 0){
//                    messages("Empty Database", "Sorry, No data was found");
//                }else{
//                    //proceed to display the selected data
//                    //Use the String buffer to append and display the records
//                    StringBuffer buffer = new StringBuffer();
//
//                    //loop through the selected data that is on your cursor to display
//                    while(cursor.moveToNext()){
//                        buffer.append(cursor.getString(0)+"\t");//zero index - column for userName
//                        buffer.append(cursor.getString(1)+"\t");//first index - column for email
//                        buffer.append(cursor.getString(2)+"\n");//second index - column for phoneNumber
//                        buffer.append(cursor.getString(3)+"\n");//third index - column for password
//                    }
//                    //display your data using the string buffer on the message display
//                    messages("DATABASE RECORDS", buffer.toString());
//                }
            }
        });

        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(Signup.this, MainActivity.class);
                startActivity(go);
            }
        });

        //create a database if exists.
        // first we eed to import the SQLite database
        db = openOrCreateDatabase("carrental", MODE_PRIVATE, null);

        //create table or tables for the database
        db.execSQL("CREATE TABLE IF NOT EXISTS customers(username VARCHAR, email VARCHAR, phone INTEGER, password VARCHAR)");

        username = (EditText) findViewById(R.id.edt_sign_username);
        email = (EditText) findViewById(R.id.edt_sign_email);
        phone_number = (EditText) findViewById(R.id.edt_sign_phone);
        password = (EditText) findViewById(R.id.edt_sign_password);
        confirm_password = (EditText) findViewById(R.id.edt_sign_password_confirm);
        agree = (RadioButton) findViewById(R.id.radiobtn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myusername = username.getText().toString();
                String myemail = email.getText().toString();
                String myphoneNumber = phone_number.getText().toString();
                String mypassword = password.getText().toString();
                String myconfirm_password = confirm_password.getText().toString();

                if (myusername.isEmpty()) {
//                    messages("ERROR!!", "UserName field is empty");
                    username.setError("UserName required");
                } else if (myemail.isEmpty()) {
//                    messages("ERROR!!", "Email field is empty");
                    email.setError("Email required");
                } else if (myphoneNumber.isEmpty()) {
//                    messages("ERROR!!", "Add Phone number to continue");
                    phone_number.setError("Phone number required");
                } else if (mypassword.isEmpty()) {
//                    messages("ERROR!!", "A Password is required to continue");
                    password.setError("Create a password");
                } else if (myconfirm_password.isEmpty()) {
//                    messages("ERROR!!", "Confirm your password to continue");
                    confirm_password.setError("Password confirmation required");
                } else if (!(mypassword.equals(myconfirm_password))) {
//                    Toast.makeText(Signup.this, "The passwords you entered do not much", Toast.LENGTH_SHORT).show();
                    confirm_password.setError("Error!! Password Mismatch");
                }else if (!(agree.isChecked())){
//                    agree.setError("Kindly agree to our terms and conditions");
                      messages("ERROR!!", "Agree to the terms and conditions to continue");

                }                else{
                    db.execSQL("INSERT INTO customers VALUES('"+myusername+"','"+myemail+"','"+myphoneNumber+"','"+mypassword+"')");
//                    messages("SUCCESS", "Sign Up Successful");
                    Toast.makeText(Signup.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                        Intent go = new Intent(getBaseContext(), MainActivity.class);
                        go.putExtra("username", username.getText().toString());
                        startActivity(go);

                        username.setText("");
                        email.setText("");
                        phone_number.setText("");
                        password.setText("");
                        confirm_password.setText("");
                    }
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
