package com.example.admin.hvacapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
Button b,tv,t;
TextView tx,text;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.b);
        text=findViewById(R.id.text);
        tv=findViewById(R.id.tv);  t=findViewById(R.id.t);
        tx=findViewById(R.id.tx);
tx.setText("sign in");
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        if(mAuth.getCurrentUser()!=null){text.setText("Signed in as "+user.getEmail());tx.setText("");}
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()!=null) {   Toast.makeText(MainActivity.this, "YOU ARE ALREADY SIGNED ", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "CLICK SIGNOUT ", Toast.LENGTH_SHORT).show();
                    return;
                }               Intent i = new Intent(MainActivity.this,signupActivity.class);
                startActivity(i);
                finish();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,loginActivity.class);
                startActivity(i);
                finish();
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null) {
                    mAuth.signOut();}
                   if (mAuth.getCurrentUser() == null) {
                       Intent i = new Intent(MainActivity.this,MainActivity2.class);
                       startActivity(i);
finish();

               }}

        });
    }
}
