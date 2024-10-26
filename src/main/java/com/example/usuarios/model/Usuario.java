package com.example.usuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        nullable = false,
        name= "id_usuario"
    )
    private Long id;
    
    @Column(
        nullable = false,
        name= "username"
    )
    private String username;
    
    @Column(
        nullable = false,
        name= "nombre"
    )
    private String nombre;

    @Column(
        nullable = false,
        name= "apellido"
    )
    private String apellido;

    @Column(
        nullable = false,
        name= "pass"
    )
    private String password;
    
    
    public Usuario() {}

    public Usuario(String username, String nombre, String apellido, String password
        ) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> validarCampos() {
        List<String> mensajes = new ArrayList<>();
    
        if (!validarUsername()) {
            mensajes.add("El username debe tener entre 5 y 20 caracteres.");
        }
        if (!validarNombre()) {
            mensajes.add("El nombre debe tener entre 2 y 50 caracteres.");
        }
        if (!validarApellido()) {
            mensajes.add("El apellido debe tener entre 2 y 50 caracteres.");
        }
        if (!validarPassword()) {
            mensajes.add("La contraseña debe tener entre 6 y 30 caracteres.");
        }
    
        return mensajes;
    }
    
    private boolean validarUsername() {
        return username != null && username.length() >= 5 && username.length() <= 20;
    }
    
    private boolean validarNombre() {
        return nombre != null && nombre.length() >= 2 && nombre.length() <= 50;
    }
    
    private boolean validarApellido() {
        return apellido != null && apellido.length() >= 2 && apellido.length() <= 50;
    }
    
    private boolean validarPassword() {
        return password != null && password.length() >= 6 && password.length() <= 30;
    }
    
    public String getFullName() {
        return nombre + " " + apellido;
    }
}
