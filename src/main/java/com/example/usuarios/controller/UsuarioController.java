package com.example.usuarios.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.usuarios.service.UsuarioService;
import com.example.usuarios.model.Usuario;
import com.example.usuarios.model.ApiResponse;
import com.example.usuarios.model.Login;


import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> getUsers(){
        return usuarioService.getUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse<Usuario>> getUserById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);

        if (usuario == null) {
            return ResponseEntity.ok()
                    .body(new ApiResponse<>("Usuario no encontrado", false, null));
        }
        return ResponseEntity.ok(new ApiResponse<>("Usuario encontrado", true, usuario));
    }

    // @GetMapping("/usuarios/{id}")
    // public Usuario getUserById(@PathVariable Long id) {
        
    //     // return usuarioService.getUsuarioById(id);
    // }
    
    @PostMapping("/usuarios/add")
    public ResponseEntity<ApiResponse<Usuario>> createUser(@RequestBody Usuario usuario) {
        //Lista de errores en validaciones
        List<String> errores = usuario.validarCampos();

        Usuario user = usuarioService.getUsuarioByUsername(usuario.getUsername());

        if(user!=null){
            // errores.add("Ya existe un usuario con el nombre de usuario: "+user.getUsername());
            return ResponseEntity.badRequest().body(new ApiResponse<>("Ya existe un usuario con el nombre de usuario: "+user.getUsername(), false, null));
        }

        if (errores.isEmpty()) {
            // return ResponseEntity.ok(usuarioService.createUsuario(usuario));
            return ResponseEntity.ok().body(new ApiResponse<>("Usuario creado correctamente.", true, usuarioService.createUsuario(usuario)));

        }

        // return ResponseEntity.badRequest().body(errores);
        return ResponseEntity.badRequest().body(new ApiResponse<>("Credenciales incorrectas!", false, null));

    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Usuario>> LoginUser(@RequestBody Login log) {
        // List<String> mensajes = new ArrayList<>();
        
        Usuario user = usuarioService.getUsuarioByUsername(log.getUsername());

        if(user == null){
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                "No existe usuario con el nombre de usuario: "+log.getUsername(), 
                false, 
                null
                )
            );
        }

        if(user.getPassword().trim().equals(log.getPassword().trim())){
            return ResponseEntity.ok().body(new ApiResponse<>("Bienvenido "+user.getFullName()+" ! Acceso correcto", true, user));

        }
        // mensajes.add("Contraseña incorrecta.");
        // Si hay mensajes, devolver una respuesta con código de error 400 (BadRequest) y la lista de mensajes
        // return ResponseEntity.badRequest().body(mensajes);
        return ResponseEntity.badRequest().body(new ApiResponse<>("Contraseña incorrecta.", false, null));

    }
    @PutMapping("/usuarios/{id}")
    public Usuario updateUser(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }
    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, String>> deleteUsuario(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();

        if(usuarioService.deleteUsuario(id)){
            response.put("mensaje", "Usuario eliminado exitosamente con ID: " + id);
            return ResponseEntity.ok().body(response);
        }
        response.put("mensaje", "No existe registro con el ID:"+id);
        return ResponseEntity.badRequest().body(response);
    }


}



