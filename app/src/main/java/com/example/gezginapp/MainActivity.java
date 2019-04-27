package com.example.gezginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button kayitolBtn;
    Button girisyapBtn;
    FirebaseAuth firebaseAuth;
    EditText email;
    EditText parola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kayitolBtn = findViewById(R.id.kayitol);
        girisyapBtn = findViewById(R.id.girisyap);
        email = findViewById(R.id.email);
        parola = findViewById(R.id.parola);

        girisyapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailstr = email.getText().toString().trim();
                String parolastr = parola.getText().toString().trim();

                if(!emailstr.isEmpty() && !parolastr.isEmpty())
                {
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(emailstr,parolastr)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(getApplicationContext(),"Giriş Yapıldı",Toast.LENGTH_LONG)
                                                .show();
                                        Intent i = new Intent(MainActivity.this,NavigiationDrawerActivity.class);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Giriş Başarısız",Toast.LENGTH_LONG)
                                                .show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast
                            .makeText(getApplicationContext(),"Lütfen bilgilerinizi eksiksiz doldurun",Toast.LENGTH_LONG)
                            .show();
                }

            }
        });


        kayitolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
