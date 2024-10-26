package com.example.usuarios.repository;

import com.example.usuarios.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;


public interface  UsuarioRepository extends JpaRepository<Usuario,Long>{
    // @Query(value = "SELECT MAX(u.id) FROM usuario u")
    // Long findMaxId();
    Usuario findByUsername(String username);
}

