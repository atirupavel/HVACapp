package com.example.admin.hvacapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signupActivity extends AppCompatActivity implements View.OnClickListener {
    Button b;
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

                b = (Button) findViewById(R.id.b);
                e1 = (EditText) findViewById(R.id.e1);
                e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);

        b.setOnClickListener(this);
            }

            public void signUp() {
                String e = e1.getText().toString();
                String ee = e2.getText().toString();
                String eee = e3.getText().toString().trim();
                String eeee = e4.getText().toString();
                if (TextUtils.isEmpty(e)) {
                    Toast.makeText(this, "please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }  if (TextUtils.isEmpty(ee)) {
                    Toast.makeText(this, "please enter address", Toast.LENGTH_SHORT).show();
                    return;
                }  if (TextUtils.isEmpty(eee)) {
                    Toast.makeText(this, "please enter mobile number ", Toast.LENGTH_SHORT).show();
                    return;
                } if (TextUtils.isEmpty(eeee)) {
                    Toast.makeText(this, "please enter state and city", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(num(ee)==false){  Toast.makeText(this, "mobile number should be a number", Toast.LENGTH_SHORT).show();
                    return;}
                {

                    Intent i=new Intent(signupActivity.this,signupActivity2.class);

                i.putExtra("name",e);
i.putExtra("address",ee);
i.putExtra("mobilenumber",eee);
i.putExtra("stateandcity",eeee);
                    startActivity(i);
                finish();}
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



