package com.example.ricar.tema4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricar.tema4.Model.Usuarios;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatosUsuario extends AppCompatActivity {
    EditText txtNombreUsuario;
    EditText txtNombre;
    EditText txtApellidos;
    EditText txtDirreccion;
    Button enviar;

    DatabaseReference baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);
        baseDatos = FirebaseDatabase.getInstance().getReference("usuario");


        txtNombreUsuario = findViewById(R.id.nombreUsuario);
        txtNombre = findViewById(R.id.nombreReal);
        txtApellidos = findViewById(R.id.apellidos);
        txtDirreccion = findViewById(R.id.dirrecion);
        enviar = findViewById(R.id.enviarDatos);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = txtNombreUsuario.getText().toString();
                String nombre = txtNombre.getText().toString();
                String apellidos = txtApellidos.getText().toString();
                String dirreccion = txtDirreccion.getText().toString();

                if (TextUtils.isEmpty(nombreUsuario))
                {
                    Toast.makeText(getApplicationContext(), "Error, Debes introducir el Nombre de Usuario", Toast.LENGTH_LONG).show();
                        if (TextUtils.isEmpty(nombre))
                        {
                            Toast.makeText(getApplicationContext(), "Error, Debes introducir el Nombre", Toast.LENGTH_LONG).show();
                            if (TextUtils.isEmpty(apellidos))
                            {
                                Toast.makeText(getApplicationContext(), "Error, Debes introducir los Apellidos", Toast.LENGTH_LONG).show();
                                if (TextUtils.isEmpty(dirreccion))
                                {
                                    Toast.makeText(getApplicationContext(), "Error, Debes introducir la Dirrección", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                    else
                    {
                        Usuarios u = new Usuarios(nombreUsuario, nombre, apellidos, dirreccion);
                        String clave = baseDatos.push().getKey();
                        baseDatos.child(clave).setValue(u);
                        Toast.makeText(getApplicationContext(), "Se añadio un Nuevo usuario: [ "+nombreUsuario+" ]", Toast.LENGTH_LONG).show();
                    }



                }
        });
    }
}
