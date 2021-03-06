package com.example.ricar.tema4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaInicial extends AppCompatActivity implements View.OnClickListener {
    Button agregarUsuario;
    Button modificarDatos;
    Button agreagarProducto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);

        agregarUsuario = findViewById(R.id.agregarUsuarios);
        modificarDatos = findViewById(R.id.modificarDatos);
        agreagarProducto = findViewById(R.id.añadirProducto);

        agregarUsuario.setOnClickListener(this);
        modificarDatos.setOnClickListener(this);
        agreagarProducto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.agregarUsuarios:
                Intent i1 = new Intent(getApplicationContext(), DatosUsuario.class);
                startActivity(i1);
                break;
            case R.id.modificarDatos:
                Intent i2 = new Intent(getApplicationContext(), ModificarDatos.class);
                startActivity(i2);
                break;
            case R.id.añadirProducto:
                Intent i3 = new Intent(getApplicationContext(), AgregarProducto.class);
                startActivity(i3);
                break;
        }
    }
}
