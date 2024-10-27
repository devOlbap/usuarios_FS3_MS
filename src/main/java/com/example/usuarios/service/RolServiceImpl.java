package com.example.usuarios.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarios.model.Rol;
import com.example.usuarios.repository.RolRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> getRoles(){
        return rolRepository.findAll();
    }

    @Override
    public Rol getRolById(Long id){
        Optional<Rol> rolOP = rolRepository.findById(id);
        if (rolOP.isPresent()) {
            return rolOP.get(); 
        } else {
            return null; 
        }
    }
    @Override
    public Rol createRol(Rol rol){
        return rolRepository.save(rol);
    }
    @Override
    public Rol updateRol(Long id, Rol rol){

        if(rolRepository.existsById(id)){
            rol.setId(id);
            return rolRepository.save(rol);
        }
        return null;
    }
    @Override
    public Boolean deleteRol(Long id){
        if(rolRepository.existsById(id)){
            rolRepository.deleteById(id);
            return true;
        }
        return false;
    }

  
}
