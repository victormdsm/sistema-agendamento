package com.caravana.agendamento.model.service;

import com.caravana.agendamento.model.entities.Usuario;
import com.caravana.agendamento.model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    public Usuario save(Usuario Usuario) {
        return repository.save(Usuario);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }



}
