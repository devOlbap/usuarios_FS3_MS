package com.example.usuarios.repository;

import com.example.usuarios.model.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;


public interface  RolRepository extends JpaRepository<Rol,Long>{
}
