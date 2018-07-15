package com.example.admin.hvacapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class loginActivity2 extends AppCompatActivity {
    EditText e1;
    EditText e2;
    Button b,bu;
    ProgressDialog p;
    RadioButton rb;
    RadioGroup rg;


    FirebaseAuth mAuth;
    int select=0;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mAuth = FirebaseAuth.getInstance();
        String name= getIntent().getStringExtra("name") ;
        String user= getIntent().getStringExtra("user");

bu=findViewById(R.id.bu);

            b=findViewById(R.id.b);
            rg=findViewById(R.id.rg);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    select=rg.getCheckedRadioButtonId();
                    rb=(RadioButton)findViewById(select);
                    if(select==R.id.rb1){String name= getIntent().getStringExtra("name") ;

                            Intent i = new Intent(loginActivity2.this, bankdetailsActivity.class);
                            i.putExtra("name", name);
                            startActivity(i);

                      }
                    if(select==R.id.rb2){String name= getIntent().getStringExtra("name") ;
                        Intent i = new Intent(loginActivity2.this,viewActivity.class);
                        i.putExtra("name",name);
                        startActivity(i);

                    }
                    if(select==R.id.rb3){String name= getIntent().getStringExtra("name") ;
                        Intent i = new Intent(loginActivity2.this,ratingActivity2.class);
                        i.putExtra("name",name);
                        startActivity(i);

                    }
                    if(select==0){ Toast.makeText(loginActivity2.this,"press", Toast.LENGTH_SHORT).show();}
                      }} );
    bu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mAuth.signOut();
            if(mAuth.getCurrentUser()==null)
            {Intent i = new Intent(loginActivity2.this,MainActivity2.class);
            startActivity(i);
            finish();
        }}
    });}



        public  void rbClick(View v){ select=rg.getCheckedRadioButtonId();
            rb=(RadioButton)findViewById(select);

            }}

