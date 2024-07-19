package com.caravana.agendamento.dto.usuariosdto;

import com.caravana.agendamento.model.entities.Usuario;
import com.caravana.agendamento.model.enums.Ward;

import java.time.LocalDate;


public record RetornarDadosUsuarioDTO(Long id, String email, String nome, String tipoDocumento, String numeroDocumento, LocalDate nascimento, LocalDate vacina, Ward ala) {
    public RetornarDadosUsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail(), usuario.getNome(), usuario.getTipoDoDocumento(), usuario.getNumeroDoDocumento(), usuario.getDataDeNascimento(), usuario.getDataDaVacina(), usuario.getWard());
    }
}
