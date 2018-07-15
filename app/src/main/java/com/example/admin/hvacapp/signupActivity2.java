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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signupActivity2 extends AppCompatActivity implements View.OnClickListener {
        Button b;
        EditText e1;
        EditText e2;
        EditText e3;
        EditText e4;
ProgressDialog p;
FirebaseAuth mAuth;
DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        mAuth = FirebaseAuth.getInstance();
        String name= getIntent().getStringExtra("name") ;
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        p= new ProgressDialog(this);
                b = (Button) findViewById(R.id.b);
                e1 = (EditText) findViewById(R.id.e1);
                e2 = (EditText) findViewById(R.id.e2);
                e3 = (EditText) findViewById(R.id.e3);
                e4 = (EditText) findViewById(R.id.e4);

                b.setOnClickListener(this);

            }

            public void signUp() {
                String PIN_Code = e1.getText().toString().trim();
                String emailId = e2.getText().toString().trim();
                String password = e3.getText().toString().trim();
                String confirm_password = e4.getText().toString().trim();

                if (TextUtils.isEmpty(PIN_Code)) { Toast.makeText(this, "please enter PIN Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(emailId)) {
                    Toast.makeText(this, "please enter emailId", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "please enter password ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirm_password)) { Toast.makeText(this, "please confirm password ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(num(PIN_Code)==false){  Toast.makeText(this, "pincode should be a number", Toast.LENGTH_SHORT).show();
                    return;}

                 if(!password.equals(confirm_password)){ Toast.makeText(this, "your confirmation password did not match your original password", Toast.LENGTH_SHORT).show();
              e3.setText("");
              e4.setText("");
              return;}
                if(password.equals(confirm_password)){
                    p.setMessage("Registering ...");
                    p.show();
                    mAuth.createUserWithEmailAndPassword(emailId,password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                       data();

                                        String name= getIntent().getStringExtra("name") ;
                                       Intent i = new Intent(signupActivity2.this,loginActivity.class);

                                       i.putExtra("name",name);
                                       startActivity(i);
                     }
                                    else {p.dismiss();
                                        String emailId = e2.getText().toString().trim();
                                        String password = e3.getText().toString().trim();
                                    mAuth.signInWithEmailAndPassword(emailId, password)
                                                .addOnCompleteListener(signupActivity2.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(signupActivity2.this, "you are already signed in", Toast.LENGTH_SHORT).show();

                                                            Intent i = new Intent(signupActivity2.this, loginActivity1.class);


                                                            startActivity(i);

                                                            finish();
                                                        } else {
                                                            Toast.makeText(signupActivity2.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                                            b.setText("try again");

                                                        }


                                                    }
                                                });
                                        Toast.makeText(signupActivity2.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                        b.setText("try again");

                                    }



                                } });}}

public void data() {
    String pin_code = e1.getText().toString().trim();
    String emailId = e2.getText().toString().trim();
    String password = e3.getText().toString().trim();
    String name=getIntent().getStringExtra("name");
    String address=getIntent().getStringExtra("address");
    String mobile_number=getIntent().getStringExtra("mobilenumber");
    String state_and_city=getIntent().getStringExtra("state and city");
    SignupInformation signupInformation = new SignupInformation(name, mobile_number, address, state_and_city,pin_code,emailId,password);
    mDatabase.child(name).child("user_information").setValue(signupInformation);
mDatabase.child("user_login").child(name).setValue(emailId);
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
            Integer.parseInt(exp);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }}


