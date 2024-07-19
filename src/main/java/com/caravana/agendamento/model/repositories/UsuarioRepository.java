package com.caravana.agendamento.model.repositories;

import com.caravana.agendamento.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
UserDetails findByEmail(String email);
}
