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

public class RegisterActivity extends AppCompatActivity {


    Button kayitolBtn2;
    EditText email;
    EditText parola;
    EditText parolaonay;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        kayitolBtn2 = findViewById(R.id.kayitolreg);
        email = findViewById(R.id.emailreg);
        parola = findViewById(R.id.parolareg);
        parolaonay = findViewById(R.id.parolaonay);

        kayitolBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailstr = email.getText().toString().trim();
                String parolastr = parola.getText().toString().trim();
                String onaystr = parolaonay.getText().toString().trim();


                if(!emailstr.isEmpty() && !parolastr.isEmpty() && !onaystr.isEmpty())
                {
                    if(parolastr.equals(onaystr))
                    {
                        firebaseAuth = FirebaseAuth.getInstance();
                       firebaseAuth.createUserWithEmailAndPassword(emailstr,parolastr).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {

                               if(task.isSuccessful())
                               {
                                   Toast
                                           .makeText(getApplicationContext(),"Kayıt başarılı",Toast.LENGTH_LONG)
                                           .show();
                                   Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                                   startActivity(i);
                               }
                               else
                               {
                                   Toast
                                           .makeText(getApplicationContext(),"Kayıt başarısız",Toast.LENGTH_LONG)
                                           .show();
                               }
                           }
                       });
                    }
                }
                else
                {
                    Toast
                            .makeText(getApplicationContext(),"Lütfen bütün bilgileri eksiksiz doldurun",Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
