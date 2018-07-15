package com.example.admin.hvacapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ratingActivity extends AppCompatActivity {
    ProgressDialog p;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    TextView t;
    Button b;
    EditText e,e2;
    String rating;String promo;
    double total=0.0;
    double rat;
double r;
    ArrayList<String> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        mAuth = FirebaseAuth.getInstance();
        String name= getIntent().getStringExtra("name") ;
       String user= getIntent().getStringExtra("user") ;
t=findViewById(R.id.t);
e=findViewById(R.id.e);
b=findViewById(R.id.b);
e2=findViewById(R.id.e2);

        array  = new ArrayList<>();

       mDatabase = FirebaseDatabase.getInstance().getReference("project_details");
        p= new ProgressDialog(this);
        mDatabase.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              ratingInformation rate = dataSnapshot.getValue(ratingInformation.class);
              t.setText(rate.getTotal_capacity());
                total=(Double.parseDouble(rate.getTotal_capacity().toString().trim())/10);
              }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Data snapshot error",""+databaseError);
            }
        });







       b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       score();
                   }

                //  i.putExtra("name",name);
                   // mDatabase.child(user).child("rating").child("name").setValue("name");
            //    mDatabase.child(user).child("rating").child(name).setValue(rat);

       });

    }
    public void score(){
      mDatabase.child("promo_code").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for(DataSnapshot ds:dataSnapshot.getChildren()){
                array.add(ds.getValue().toString());}
                promo=e2.getText().toString();
                r = Double.parseDouble(e.getText().toString());
           String v= e.getText().toString();
              if(TextUtils.isEmpty(promo)){Toast.makeText(ratingActivity.this, "no promocode added", Toast.LENGTH_SHORT).show();}
            else{  if(num(promo)==false){ Toast.makeText(ratingActivity.this, "promocode should be number", Toast.LENGTH_SHORT).show();
                  if(!array.contains(promo)) {
                      Toast.makeText(ratingActivity.this, "your promocode dosen't match", Toast.LENGTH_SHORT).show();
                  }return;}}
            if(TextUtils.isEmpty(v)){Toast.makeText(ratingActivity.this, "no promocode added", Toast.LENGTH_SHORT).show();return;}
            if(nu(v)==false){ Toast.makeText(ratingActivity.this, "rating should be number", Toast.LENGTH_SHORT).show();return;}
                rat = r + total;
                if(array.contains(promo)) {
                    rat = rat + 1.0;Toast.makeText(ratingActivity.this, "promocode is accepted and 10% rating is added", Toast.LENGTH_SHORT).show();
                    }

                    String name= getIntent().getStringExtra("name") ;String user= getIntent().getStringExtra("user");
            mDatabase.child("project_rating").child(user).child(name).setValue(rat);
            mDatabase.child("your_rating").child(name).child(user).setValue(rat);
            Toast.makeText(ratingActivity.this, "your rating is:"+String.valueOf(rat), Toast.LENGTH_SHORT).show();


            Intent i = new Intent(ratingActivity.this,loginActivity2.class);
            i.putExtra("user",user);i.putExtra("name",name);//i.putExtra("promo",promo);
            startActivity(i);finish();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("Data snapshot error",""+databaseError);
        }
    });}
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


    boolean nu(String exp)
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
