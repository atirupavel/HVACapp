package com.example.admin.hvacapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ratingActivity2 extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;  ArrayList<String> array;  int total;Button b;ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating2);
        mAuth = FirebaseAuth.getInstance();
        b=findViewById(R.id.b);
        listview=findViewById(R.id.listview);
        String name= getIntent().getStringExtra("name") ;
        String user= getIntent().getStringExtra("user") ;    array  = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("project_details");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= getIntent().getStringExtra("name") ;String user= getIntent().getStringExtra("user");
                Intent i = new Intent(ratingActivity2.this,loginActivity2.class);
                i.putExtra("user",user);i.putExtra("name",name);
                startActivity(i);finish();
            }
        });
        mDatabase.child("your_rating").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                   array.add(ds.getKey().toString()+":   "+ds.getValue().toString());

                    }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ratingActivity2.this, android.R.layout.simple_list_item_1, array);
               listview.setAdapter(adapter);
            }
                @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Data snapshot error",""+databaseError);
            }
        });



    }
}
