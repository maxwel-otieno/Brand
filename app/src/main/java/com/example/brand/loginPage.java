package com.example.brand;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class loginPage extends AppCompatActivity {

    ListView myList;
    TextView myName;
    Button home;
    String name;
    String Click[] = {"HIRE", "HIRE", "HIRE", "HIRE", "HIRE", "HIRE", "HIRE"};
    String carName[] = {"Prado Tx","Mercedes Benz c200","Porsche","Chevrolet","Supra","Lamborghini", "Subaru"};
    String carPrice[] = {"$160 / day", "$200 / day", "$120 / day", "$210 / day", "$99 / day", "$28/Day", "$50 / Day"};
    int image[] = {R.drawable.prado_, R.drawable.mercedes_, R.drawable.porsche, R.drawable.chevrolet_2, R.drawable.supra, R.drawable.lambo, R.drawable.subaru};


    //message display function
    public void messages(String title, String message){
        AlertDialog.Builder myAlert;
        myAlert = new AlertDialog.Builder(this);
        myAlert.setTitle(title);
        myAlert.setMessage(message);
        myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(loginPage.this, "Yes was clicked", Toast.LENGTH_SHORT).show();
                Intent hire = new Intent(getBaseContext(), userAccount.class);
            }
        });
        myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent go = new Intent(getBaseContext(), loginPage.class);
                startActivity(go);
            }
        });
        myAlert.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        myName = (TextView) findViewById(R.id.textView_loggedIn);
        name = getIntent().getExtras().getString("myName");

        myName.setText("USER : "+name.toUpperCase());
        myName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(loginPage.this, ""+name+" clicked", Toast.LENGTH_SHORT).show();

                Intent go = new Intent(getBaseContext(), userAccount.class);
//                go.putExtra("myName", username.getText().toString());
                startActivity(go);
            }
        });

        home = (Button) findViewById(R.id.btn_logout);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(loginPage.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        myList = (ListView) findViewById(R.id.listView);

        //create an array adapter class

        myAdapter adapter = new myAdapter(this, carName, carPrice, image, Click);
        myList.setAdapter(adapter);

        //set item click on listview
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0){
                    Toast.makeText(loginPage.this, "LandCruiser Prado", Toast.LENGTH_SHORT).show();
                }if (position == 1){
                    Toast.makeText(loginPage.this, "Mercedes Benz C200", Toast.LENGTH_SHORT).show();
                }if (position == 2){
                    Toast.makeText(loginPage.this, "Porsche Prado", Toast.LENGTH_SHORT).show();
                }if (position == 3){
                    Toast.makeText(loginPage.this, "Chevrolet Prado", Toast.LENGTH_SHORT).show();
                }if (position == 4){
                    Toast.makeText(loginPage.this, "Supra Prado", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    class myAdapter extends ArrayAdapter<String>{

        Context context;
        String rCarName[];
        String rPrice[];
        int rImage[];
        String rClick[];

        myAdapter (Context c, String carName[], String Price[], int images[], String Click[]){
            super(c, R.layout.overview, R.id.textView_1, carName);
            this.context = c;
            this.rCarName = carName;
            this.rPrice = Price;
            this.rImage = images;
            this.rClick = Click;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater myInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View overview = myInflater.inflate(R.layout.overview, parent, false);
            ImageView images = overview.findViewById(R.id.my_image);
            final TextView myCarName = overview.findViewById(R.id.textView_1);
            TextView myCarPrice = overview.findViewById(R.id.textView_2);
            Button myClickButton = overview.findViewById(R.id.btn_conf);

            //then set the resources on the views

            images.setImageResource(rImage[position]);
            myCarName.setText(rCarName[position]);
            myCarPrice.setText(rPrice[position]);
            myClickButton.setText(rClick[position]);

            myClickButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "You're Buying a "+rCarName[position], Toast.LENGTH_SHORT).show();
                    messages("Hire Now", "Your cost per day will be "+rPrice[position]+"\n" +"\n"+
                            "Would you like to continue?");

                    Intent hire = new Intent(getBaseContext(), userAccount.class);
                    hire.putExtra("myHiredCar",rCarName.toString());
                    startActivity(hire);
                }
            });
            return overview;
        }
    }

}
