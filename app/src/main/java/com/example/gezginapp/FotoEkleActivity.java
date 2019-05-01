package com.example.gezginapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static java.lang.Thread.sleep;

public class FotoEkleActivity extends AppCompatActivity {

    ImageView foto;
    Button fotosec;
    Button fotokaydet;
    FirebaseStorage firebaseStorage;
    Uri pathfile;
    static final int IMAGE_REQUEST = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_ekle);
        foto=findViewById(R.id.secilenfoto);
        fotosec = findViewById(R.id.fotosec);
        fotokaydet = findViewById(R.id.fotokaydet);
        firebaseStorage = FirebaseStorage.getInstance();

        fotosec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,"Resim Seçiniz"),IMAGE_REQUEST);
            }
        });

        fotokaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePhoto();
                SystemClock.sleep(10000);
                Intent i = new Intent(FotoEkleActivity.this,NavigiationDrawerActivity.class);
                i.putExtra("Intent Bilgisi","fotoekle");
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
            pathfile = data.getData();

        }

        try{
            Picasso.with(FotoEkleActivity.this).load(pathfile).fit().centerCrop().into(foto);
        }catch (Exception e)
        {
            Log.e("FOTOGRAF YERLESTIRME",
                    "Fotoğraf yerleştirme sırasında bir exception ile karşılaşıldı");
        }
    }

    public void savePhoto()
    {
        if(pathfile!=null){
            StorageReference storageReference = firebaseStorage.getReference();
            storageReference.child("userprofilephoto").putFile(pathfile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getBaseContext(),"Fotoğraf kaydetme işlemi başarılı",
                            Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getBaseContext(),"Fotoğraf kaydetme işlemi başarısız",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
