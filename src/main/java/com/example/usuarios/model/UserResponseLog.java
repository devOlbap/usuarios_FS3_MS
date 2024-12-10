package com.example.usuarios.model;

public class UserResponseLog<T> {
    private String nombre;
    private String apellido;
    private String token;



    public UserResponseLog(String nombre, String apellido, String token) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.token = token;

    }

    // Agrega getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }
    public String getapellido() {
        return apellido;
    }

    public void setapellido(String apellido) {
        this.apellido = apellido;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
