package com.example.ricar.tema4.Model;

public class Usuarios {
    private String email;
   private String nombreUsuario;
   private String nombre;
   private String apellidos;
   private String dirreccion;


    public Usuarios(String email, String nombreUsuario, String nombre, String apellidos, String dirreccion) {
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dirreccion = dirreccion;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "email='" + email + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dirreccion='" + dirreccion + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }


}
