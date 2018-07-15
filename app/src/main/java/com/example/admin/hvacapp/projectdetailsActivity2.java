package com.example.admin.hvacapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class projectdetailsActivity2 extends AppCompatActivity implements View.OnClickListener {
Button b,bu,but;
RadioGroup rg;
     ImageView image;
String type;
    ProgressDialog mProgressDialog;
int select;

    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseAuth auth;
    final int PICK_IMAGE_REQUEST=71;
    Uri filepath;
RadioButton rb;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetails2);
        auth = FirebaseAuth.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
   b=findViewById(R.id.b);
   image=findViewById(R.id.image);
        but=findViewById(R.id.but);
        bu=findViewById(R.id.bu);
        b.setOnClickListener(this);
        bu.setOnClickListener(this);
        but.setOnClickListener(this);
        String name= getIntent().getStringExtra("name") ;
rg=findViewById(R.id.rg);

        FirebaseUser user=auth.getCurrentUser();
    }

  public  void rbClick(View v){ select=rg.getCheckedRadioButtonId();
        rb=(RadioButton)findViewById(select);}

    public void uploadImage(){
        if(filepath!=null){
            final       ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("UPLOADING..");
            progressDialog.show(); String name= getIntent().getStringExtra("name") ;
            StorageReference reference=  storageReference.child("images/"+name);
            reference.putFile(filepath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    progressDialog.dismiss();
                    Toast.makeText(projectdetailsActivity2.this,type+"-project uploaded",Toast.LENGTH_SHORT).show();
                    Toast.makeText(projectdetailsActivity2.this," click save when finished",Toast.LENGTH_SHORT).show();



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(projectdetailsActivity2.this,"failed",Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("uploaded"+(int)progress+"%");
                            String name= getIntent().getStringExtra("name") ;
                            Intent i = new Intent(projectdetailsActivity2.this, loginActivity2.class);
                            i.putExtra("name",name);
                            startActivity(i);
                            finish();
                        }
                    });
        }
    }
    public void chooseImage(){
        Intent i = new Intent();
        i.setType("image/+");
        i.setAction((Intent.ACTION_GET_CONTENT));
        startActivityForResult(Intent.createChooser(i, "select picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData() !=null){filepath= data.getData();
            {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                    image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }}}}



        @Override
    public void onClick(View v) {
switch(v.getId())
{
    case R.id.b: String name= getIntent().getStringExtra("name") ;
        Intent i = new Intent(projectdetailsActivity2.this, loginActivity2.class);
        i.putExtra("name",name);
        startActivity(i);
        finish();
        break;
    case R.id.bu:
type="";
        select=rg.getCheckedRadioButtonId();
        rb=(RadioButton)findViewById(select);

      if(select==R.id.rb1){type="window" ;}
            if(select==R.id.rb2){type="split  ";}
            if(select==R.id.rb3){type="VRV-VRF  ";}
            if(select==R.id.rb4){type="ductable  ";}
            if(select==R.id.rb5){type="chiller ";}
            if(select==R.id.rb6){type="ventillation ";}
        if(type==""){Toast.makeText(projectdetailsActivity2.this,"select project type",Toast.LENGTH_SHORT).show();}
         else{   chooseImage();}break;
    case R.id.but:
        uploadImage();

}
    }
}
