package com.example.admin.hvacapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bankdetailsActivity extends AppCompatActivity implements View.OnClickListener {
    Button b;
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    EditText e5;
    ProgressDialog p;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankdetails);

        mAuth = FirebaseAuth.getInstance();
       String name= getIntent().getStringExtra("name") ;
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        p= new ProgressDialog(this);
        b = (Button) findViewById(R.id.b);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        b.setOnClickListener(this);

    }

    public void signUp() {
        String acc = e1.getText().toString().trim();
        String bank = e2.getText().toString().trim();
        String branch = e3.getText().toString().trim();
        String accno = e4.getText().toString().trim();
        String ifci = e5.getText().toString().trim();
        if (TextUtils.isEmpty(acc)) {
            Toast.makeText(this, "please enter account holder name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bank)) {
            Toast.makeText(this, "please enter bank name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(branch)) {
            Toast.makeText(this, "please enter branch ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(accno)) {
            Toast.makeText(this, "please enter account number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ifci)) {
            Toast.makeText(this, "please enter IFCI code", Toast.LENGTH_SHORT).show();
            return;
        }


        p.setMessage("Registering ...");
        p.show();

        BankInformation bankInformation = new BankInformation(acc,bank,branch,accno,ifci);
        String name= getIntent().getStringExtra("name") ;
        mDatabase.child(name).child("bank_details").setValue(bankInformation);
        Intent i=new Intent(this,projectdetailsActivity.class);
          i.putExtra("name",name);
        startActivity(i);
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.b){

            signUp();


        }
    }
}
