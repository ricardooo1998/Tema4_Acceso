package com.example.ricar.tema4;

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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class ActivityRegistrar extends AppCompatActivity implements View.OnClickListener {
    Button registrar;
    Button cancelar;
    EditText login;
    EditText contrasenia;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        registrar = findViewById(R.id.registrar);
        cancelar = findViewById(R.id.cancelar);
        login = findViewById(R.id.email);
        contrasenia = findViewById(R.id.password);

        registrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.cancelar:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
            case R.id.registrar:
                String email = login.getText().toString();
                String password = contrasenia.getText().toString();
                registrarUsuario(email, password);
                break;

        }
    }

    private void registrarUsuario(final String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(ActivityRegistrar.this, "Autentificación Correcta."+user.getUid(),
                                    Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(ActivityRegistrar.this, "Error, Ya existe un usuario con el email: "+email,
                                        Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(ActivityRegistrar.this, "Fallo en la Autentificación.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

    }
}
