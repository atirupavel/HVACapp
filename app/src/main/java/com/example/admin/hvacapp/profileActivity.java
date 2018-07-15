package com.example.admin.hvacapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileActivity extends AppCompatActivity implements View.OnClickListener{
CheckBox checkBox1,checkBox2,checkBox4,checkBox3,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,checkBox10,checkBox11,checkBox12;
CheckBox checkBox15,checkBox14,checkBox13;
Button b;
EditText e;
    FirebaseAuth mAuth;
    String name1,name2,name3,name4,name5,name6,name7,name8,name9,name10,name11,name12,name13,name14,name15,exp;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       String name= getIntent().getStringExtra("name") ;
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        if (mAuth.getCurrentUser() != null) {
          //  finish();
          //  startActivity(new Intent(getApplicationContext(), loginActivity.class));
            }

            checkBox1 = findViewById(R.id.checkBox);
            checkBox2 = findViewById(R.id.checkBox2);
            checkBox3 = findViewById(R.id.checkBox3);
            checkBox4 = findViewById(R.id.checkBox4);
            checkBox5 = findViewById(R.id.checkBox5);
            checkBox6 = findViewById(R.id.checkBox6);
            checkBox7 = findViewById(R.id.checkBox7);
            checkBox8 = findViewById(R.id.checkBox8);
            checkBox9 = findViewById(R.id.checkBox9);
            checkBox10 = findViewById(R.id.checkBox10);
            checkBox11 = findViewById(R.id.checkBox11);
            checkBox12 = findViewById(R.id.checkBox12);
            checkBox13 = findViewById(R.id.checkBox13);
            checkBox14 = findViewById(R.id.checkBox14);
            checkBox15 = findViewById(R.id.checkBox15);
            e = findViewById(R.id.e);
            b = findViewById(R.id.b);
            b.setOnClickListener(this);
            checkBox1.setOnClickListener(this);
            checkBox2.setOnClickListener(this);
            checkBox3.setOnClickListener(this);
            checkBox4.setOnClickListener(this);
            checkBox5.setOnClickListener(this);
            checkBox6.setOnClickListener(this);
            checkBox7.setOnClickListener(this);

            checkBox8.setOnClickListener(this);
            checkBox9.setOnClickListener(this);
            checkBox10.setOnClickListener(this);
            checkBox11.setOnClickListener(this);

            checkBox12.setOnClickListener(this);
            checkBox13.setOnClickListener(this);
            checkBox14.setOnClickListener(this);
            checkBox15.setOnClickListener(this);


        }
        public void login () { String name= getIntent().getStringExtra("name") ;
            {
                exp=e.getText().toString().trim();

                  UserInformation userInformation = new UserInformation( name1,name2,name3,name4,name5,name6,name7,name8,name9,name10,name11,name12,name13,name14,name15,exp);

                if(num(exp)==false)
                {Toast.makeText(profileActivity.this,"years should be a number",Toast.LENGTH_SHORT).show();
return;
                }   mDatabase.child(name).child("profile_details").setValue(userInformation);
                Intent i = new Intent(profileActivity.this,loginActivity2.class);
                i.putExtra("name",name);
                startActivity(i);
                finish();
                }}
        @Override
        public void onClick (View v){

            switch (v.getId()) {
                case R.id.checkBox: {
                    if (checkBox1.isChecked()) {
                        name1="experience for window service";break;
                    }


                }
                case R.id.checkBox2: {
                    if (checkBox2.isChecked()) {
                        name2="experience for window installation";break;
                    }}


                        case R.id.checkBox3: {
                            if (checkBox3.isChecked()) {
                                name3= "experience for window complaint";
                                break;}
                        }
                            case R.id.checkBox4: {
                                if (checkBox4.isChecked()) {
                                    name4 = "experience for split service";
                                    break; }       }
                                case R.id.checkBox5: {
                                    if (checkBox5.isChecked()) {
                                        name5 = "experience for split installation";
                                    }
                                    break;}case R.id.checkBox6: {
                                        if (checkBox6.isChecked()) {
                                            name6 = "experience for split complaint";
                                            break;}       }
                                        case R.id.checkBox7: {
                                            if (checkBox7.isChecked()) {
                                                name7= "experience for ductable service";
                                                break;  }
                                        }           case R.id.checkBox8: {
                    if (checkBox8.isChecked()) {
                        name8 = "experience for ductable installation";
                        break;}
                } case R.id.checkBox9: {
                                                    if (checkBox9.isChecked()) {
                                                        name9 = "experience for ductable complaint";
                                                        break;                      }
                                                }           case R.id.checkBox10: {
                                                        if (checkBox10.isChecked()) {
                                                            name10 = "experience for VRV/VRF service ";
                                                            break;        }                                       }
                                                        case R.id.checkBox11: {
                                                            if (checkBox11.isChecked()) {
                                                                name11= "experience for VRV/VRF installation";
                                                                break;}
                                                        }   case R.id.checkBox12: {
                    if (checkBox12.isChecked()) {
                        name12 = "experience for VRV/VRF complaint ";
                        break;   }
                }       case R.id.checkBox13: {
                    if (checkBox13.isChecked()) {
                        name13 = "experience for chiller service";
                        break;}
                }                                                   case R.id.checkBox14: {
                                                                        if (checkBox14.isChecked()) {
                                                                            name14 = "experience for chiller installation";
                                                                            break;                    }                                                       }
                                                                        case R.id.checkBox15: {
                                                                            if (checkBox15.isChecked()) {
                                                                                name15="experience for chiller complaint";
                                                                            break;}}

                                                                            case R.id.b: {
                                                                                String exp=e.getText().toString().trim();
                                                                                if(TextUtils.isEmpty(exp)){
                                                                                    Toast.makeText(profileActivity.this,"fill in experience",Toast.LENGTH_SHORT).show();
                                                                                }
               else {
                                                                                    login();
                                                                                }
                }


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
    }
}


