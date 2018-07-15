package com.example.admin.hvacapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class loginActivity1 extends AppCompatActivity implements View.OnClickListener{
    EditText e1;
    EditText e2;
    Button b;
    ProgressDialog p;
    private FirebaseAuth mAuth;FirebaseUser user;
    String random;
    TextView t,text;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        p=new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
     user=mAuth.getCurrentUser();
        b=findViewById(R.id.b);
        mDatabase = FirebaseDatabase.getInstance().getReference("project_details");
        b.setOnClickListener(this);
        p=new ProgressDialog(this);
        String name = getIntent().getStringExtra("name");
        Random rand=new Random();
      random=  String.valueOf(rand.nextInt(10000));
      t=findViewById(R.id.t); text=findViewById(R.id.text);
      t.setText("Promo_code:  "+random);
        Toast.makeText(this, "Use promo code to get extra 10% rating", Toast.LENGTH_SHORT).show();
        text.setText("Welcome: "+name);
    }
    public  void login() {
        FirebaseUser user = mAuth.getCurrentUser();
        String email = e1.getText().toString().trim();
        String password = e2.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        p.setMessage("Registering ...");
        p.show();

        if (email.equals(user.getEmail())) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {  String name = getIntent().getStringExtra("name");
                                mDatabase.child("promo_code").child(random).setValue(random);
                                Intent i = new Intent(loginActivity1.this, profileActivity.class);

                                i.putExtra("name", name);
                                startActivity(i);
                                finish();
                            } else {
                                p.dismiss();
                                Toast.makeText(loginActivity1.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                b.setText("try again");
                            }


                        }
                    });
        } else {p.dismiss();
            Toast.makeText(loginActivity1.this, "You are signed in as "+user.getEmail() , Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        { case R.id.b:
            login();}
    }
}




