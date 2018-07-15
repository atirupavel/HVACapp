package com.example.admin.hvacapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class projectdetailsActivity extends AppCompatActivity implements View.OnClickListener{
Button b;
EditText e1,e2,e3,e4;
TextView t1;long n;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetails);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        t1=findViewById(R.id.t1);
        b=findViewById(R.id.b);
        b.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference("project_details/");

        p= new ProgressDialog(this);
    }

    public void signUp() {
        String e = e1.getText().toString();
        String ee = e2.getText().toString();
        String eee = e3.getText().toString().trim();
        String eeee = e4.getText().toString();
        if (TextUtils.isEmpty(e)) {
            Toast.makeText(this, "please enter total capacity", Toast.LENGTH_SHORT).show();
            return;
        }
        if(num(e)==false){ Toast.makeText(this, "capacity should be a number(in tons)", Toast.LENGTH_SHORT).show(); return;}
        if (TextUtils.isEmpty(ee)) {
            Toast.makeText(this, "please enter project site", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(eee)) {
            Toast.makeText(this, "please enter address ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(eeee)) {
            Toast.makeText(this, "please enter area and city", Toast.LENGTH_SHORT).show();
            return;
        }

        {  projectInformation projectinformation = new projectInformation(e,ee,eee,eeee);
            String name= getIntent().getStringExtra("name") ;
            mDatabase.child(name).setValue(projectinformation);
            mDatabase.child("project_users").child(name).setValue(name);/*addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    n= dataSnapshot.getChildrenCount();
                    Log.i("i",String.valueOf(n)); String name= getIntent().getStringExtra("name") ;
                    mDatabase.child("project_users").child(String.valueOf(n)).setValue(name);
                    }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Data snapshot error",""+databaseError);
                }
            });*/


          Intent i = new Intent(projectdetailsActivity.this, projectdetailsActivity2.class);
        i .putExtra("name",name);
startActivity(i);

        }
    }



    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.b){

            signUp();


        }
    }
    boolean num(String exp)
    {
        try
        {
            Float.parseFloat(exp);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }

}






