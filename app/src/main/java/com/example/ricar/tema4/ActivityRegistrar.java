package com.example.ricar.tema4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricar.tema4.Model.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegistrar extends AppCompatActivity implements View.OnClickListener {
    Button registrar;
    Button cancelar;
    EditText login;
    EditText contrasenia;
    EditText NombreUsuario;
    EditText Nombre;
    EditText Apellidos;
    EditText Direccion;
    private FirebaseAuth mAuth;
    private DatabaseReference ddbb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        registrar = findViewById(R.id.registrar);
        cancelar = findViewById(R.id.cancelar);
        login = findViewById(R.id.email);
        contrasenia = findViewById(R.id.password);
        NombreUsuario = findViewById(R.id.registrarNombreUsuario);
        Nombre = findViewById(R.id.registrarNombre);
        Apellidos = findViewById(R.id.registrarApellido);
        Direccion = findViewById(R.id.registrarDireccion);

        ddbb = FirebaseDatabase.getInstance().getReference("usuarios");

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
                String txtNombreUsuario = NombreUsuario.getText().toString();
                String txtNombre = Nombre.getText().toString();
                String txtApellido = Apellidos.getText().toString();
                String txtDireccion = Direccion.getText().toString();
                registrarUsuario(email, password);
                agregarDatos(email, txtNombreUsuario, txtNombre, txtApellido, txtDireccion);
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

    private void agregarDatos(String email, String txtNombreUsuario, String txtNombre, String txtApellido, String txtDireccion) {

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(), "Error, Debes introducir algun email", Toast.LENGTH_LONG).show();
            if (TextUtils.isEmpty(txtNombreUsuario))
            {
                Toast.makeText(getApplicationContext(), "Error, Debes introducir el nombre de usuario", Toast.LENGTH_LONG).show();
                if (TextUtils.isEmpty(txtNombre))
                {
                    Toast.makeText(getApplicationContext(), "Error, Debes introducir el nombre", Toast.LENGTH_LONG).show();
                    if (TextUtils.isEmpty(txtApellido))
                    {
                        Toast.makeText(getApplicationContext(), "Error, Debes introducir el apellido", Toast.LENGTH_LONG).show();
                        if (TextUtils.isEmpty(txtDireccion))
                        {
                            Toast.makeText(getApplicationContext(), "Error, Debes introducir la direccion", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        }
        else
        {
            Usuarios u = new Usuarios(email, txtNombreUsuario, txtNombre, txtApellido, txtDireccion);
            String clave = ddbb.push().getKey();
            ddbb.child(clave).setValue(u);
            Toast.makeText(getApplicationContext(), "Se añadio un Nuevo usuario: [ "+NombreUsuario+" ]", Toast.LENGTH_LONG).show();
        }
    }
}
