package com.example.admin.hvacapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    ArrayList<String> array;
    ListView listview;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mAuth = FirebaseAuth.getInstance();
        myRef= FirebaseDatabase.getInstance().getReference("project_details");
        listview=findViewById(R.id.listview);
        array  = new ArrayList<>();

        myRef.child("project_users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           // UserData sampleModel = dataSnapshot.getValue(UserData.class);
            for(DataSnapshot ds:dataSnapshot.getChildren()){


               array.add(ds.getValue().toString());


            }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(viewActivity.this,android.R.layout.simple_list_item_1,array);
               listview.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Data snapshot error",""+databaseError);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String user=array.get(position).toString();
                String name= getIntent().getStringExtra("name") ;
                Intent i = new Intent(viewActivity.this,awardActivity.class);
                i.putExtra("name",name);
                i.putExtra("user",user);
                startActivity(i);
                finish();
            }
        });
    }







}