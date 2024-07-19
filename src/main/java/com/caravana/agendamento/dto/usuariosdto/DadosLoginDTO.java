package com.caravana.agendamento.dto.usuariosdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosLoginDTO(
        @NotBlank @Email String login,
        @NotBlank String senha) {
}
