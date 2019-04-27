package com.example.gezginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotEkleActivity extends AppCompatActivity {

    EditText note;
    Button notekle;
    Button notlaradon;
    FirebaseDatabase db;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);
        note = findViewById(R.id.notsehiradi);
        notekle = findViewById(R.id.notekle);
        notlaradon = findViewById(R.id.notlaradon);

        notekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = FirebaseDatabase.getInstance();
                dbRef = db.getReference().child("GezdigimYerler");
                String noteID = dbRef.push().getKey();
                String notsehir = note.getText().toString();

                if(notsehir.length()>0)
                {
                    dbRef.child(noteID).child("SehirAdi").setValue(notsehir);
                    Toast.makeText(getBaseContext(),"İşlem Başarılı",Toast.LENGTH_LONG).show();
                    Log.i("123","Database veri yazma işlemi tamam");
                }
                else
                {
                    Toast.makeText(getBaseContext(),"İşlem Başarısız",Toast.LENGTH_LONG).show();
                    Log.i("123","Database veri yazma işlemi başarısız");
                }

                notlaradon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(NotEkleActivity.this,NavigiationDrawerActivity.class);
                        i.putExtra("Intent Bilgisi","notekle");
                        startActivity(i);
                    }
                });
            }
        });
    }
}
