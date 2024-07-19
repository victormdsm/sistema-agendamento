package com.caravana.agendamento.dto.usuariosdto;

import com.caravana.agendamento.model.enums.Ward;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CadastrarUsuarioDTO(@NotBlank @Email String email,
                                  @NotBlank String senha,
                                  @NotBlank String nome,
                                  @NotBlank String tipoDocumento,
                                  @NotBlank String numeroDocumento,
                                  @Past  LocalDate nascimento,
                                  @Past LocalDate vacina,
                                  @NotNull Integer ala) {

    public Ward getAla() {
        return Ward.valueOf(ala);
    }
}
