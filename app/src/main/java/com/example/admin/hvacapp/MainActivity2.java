package com.example.admin.hvacapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    Button b,button;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


                b=findViewById(R.id.b);
              button=findViewById(R.id.button);


                mAuth=FirebaseAuth.getInstance();
                FirebaseUser user=mAuth.getCurrentUser();

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity2.this,signupActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity2.this,loginActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

            }
        }
