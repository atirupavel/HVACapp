package com.example.admin.hvacapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class awardActivity extends AppCompatActivity {
ListView listview;
    FirebaseAuth mAuth;
ImageView imageView;
    DatabaseReference myRef;
    StorageReference storageReference;
    ArrayList<String> array;  ArrayAdapter<String> adapter;
     FirebaseStorage storage;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award);
        listview=findViewById(R.id.listview);
        mAuth = FirebaseAuth.getInstance();
        array  = new ArrayList<>();
        b=findViewById(R.id.b);
        imageView=findViewById(R.id.imageView);
        myRef= FirebaseDatabase.getInstance().getReference("project_details");
        String name= getIntent().getStringExtra("name") ;
        String user= getIntent().getStringExtra("user") ;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String name= getIntent().getStringExtra("name") ;
               String user= getIntent().getStringExtra("user") ;
                Intent i = new Intent(awardActivity.this,ratingActivity.class);
            i.putExtra("user",user);
              i.putExtra("name",name);
                startActivity(i);
                finish();
            }
        });

    myRef.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
       public void onDataChange(DataSnapshot dataSnapshot) {
            UserData sampleModel = dataSnapshot.getValue(UserData.class);
        array.add(sampleModel.getTotal_capacity());
          array.add(sampleModel.getSite());
          array.add(sampleModel.getAddress());
            array.add(sampleModel.getArea_city());
array.add(sampleModel.getRating());

           adapter = new ArrayAdapter<String>(awardActivity.this, android.R.layout.simple_list_item_1, array);
           listview.setAdapter(adapter);

        }
            @Override
       public void onCancelled(DatabaseError databaseError) {
           Log.e("Data snapshot error",""+databaseError);
       }
   });

    storageReference.child("images/"+user).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               Glide.with(getApplicationContext()).load(uri.toString()).into(imageView);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(awardActivity.this, "ERROR: Image cannot be loaded", Toast.LENGTH_SHORT).show(); // Handle any errors
            }
        });
}}
