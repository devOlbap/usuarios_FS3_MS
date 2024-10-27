package com.example.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usuarios.model.Usuario;
import com.example.usuarios.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public List<Usuario> getUsuarios(){
        return userRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Long id){
        Optional<Usuario> usrOpt = userRepository.findById(id);
        if (usrOpt.isPresent()) {
            return usrOpt.get(); 
        } else {
            return null; 
        }
    }
    @Override
    public Usuario createUsuario(Usuario usuario){

        return userRepository.save(usuario);
    }
    @Override
    public Usuario updateUsuario(Long id, Usuario usuario){

        if(userRepository.existsById(id)){
            usuario.setId(id);
            return userRepository.save(usuario);
        }
        return null;
    }
    @Override
    public Usuario getUsuarioByUsername(String username){
        Optional<Usuario> usrOpt = Optional.ofNullable(userRepository.findByUsername(username));

        if (usrOpt.isPresent()) {
            return usrOpt.get(); 
        } else {
            return null; 
        }
    }
    @Override
    public Boolean deleteUsuario(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

  
}
