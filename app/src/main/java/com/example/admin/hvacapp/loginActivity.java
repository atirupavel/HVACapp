package com.example.admin.hvacapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class loginActivity extends AppCompatActivity implements  View.OnClickListener{
EditText e1;
EditText e2;
Button b;
ProgressDialog p;
private FirebaseAuth mAuth;String name;
    DatabaseReference mDatabase; ArrayList<String> array; ArrayList<String> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDatabase= FirebaseDatabase.getInstance().getReference("users");
        p=new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        b=findViewById(R.id.b);
    b.setOnClickListener(this);
    p=new ProgressDialog(this);
        array  = new ArrayList<>();
        arr  = new ArrayList<>();

    }

    public  void login(){

       final String email = e1.getText().toString().trim();
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
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(loginActivity.this,loginActivity1.class);
                           i.putExtra("name",name);
                            startActivity(i);

                        }
                        else {p.dismiss();
                            Toast.makeText(loginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            b.setText("try again");
                        }



                    } });
        mDatabase.child("user_login").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // UserData sampleModel = dataSnapshot.getValue(UserData.class);
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    array.add(ds.getValue().toString());
                    arr.add(ds.getKey().toString());
                    }
if(array.contains(email)){
int t=array.indexOf(email);
    name=arr.get(t);
}


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Data snapshot error",""+databaseError);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        { case R.id.b:
        login();}
    }
}

