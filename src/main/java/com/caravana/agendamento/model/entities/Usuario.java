package com.caravana.agendamento.model.entities;


import com.caravana.agendamento.dto.usuariosdto.CadastrarUsuarioDTO;
import com.caravana.agendamento.model.enums.Roles;
import com.caravana.agendamento.model.enums.RolesConverter;
import com.caravana.agendamento.model.enums.Ward;
import com.caravana.agendamento.model.enums.WardConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nome;
    private String senha;
    @Column(name = "tipo_do_documento")
    private String tipoDoDocumento;
    @Column(name = "numero_do_documento")
    private String numeroDoDocumento;
    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;
    @Column(name = "data_da_vacina")
    private LocalDate dataDaVacina;

    @Convert(converter = WardConverter.class)
    @Column(name = "ala")
    private Ward ward;
    @Convert(converter = RolesConverter.class)
    private Roles role;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioHasCaravana> caravanas;

    public Usuario (CadastrarUsuarioDTO dto, PasswordEncoder passwordEncoder) {
        this.email = dto.email();
        this.nome = dto.nome();
        this.senha = passwordEncoder.encode(dto.senha());
        this.tipoDoDocumento = dto.tipoDocumento();
        this.numeroDoDocumento = dto.numeroDocumento();
        this.dataDeNascimento = dto.nascimento();
        this.dataDaVacina = dto.vacina();
        this.ward = dto.getAla();
        this.role = Roles.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Roles.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_STAKE"),
                    new SimpleGrantedAuthority("ROLE_LOCAL_LEADER"),
                    new SimpleGrantedAuthority("ROLE_USER"));}
        else if (this.role == Roles.STAKE){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_STAKE"),
                    new SimpleGrantedAuthority("ROLE_LOCAL_LEADER"),
                    new SimpleGrantedAuthority("ROLE_USER"));}
        else if (this.role == Roles.LOCAL_LEADER){
            return List.of(
                new SimpleGrantedAuthority("ROLE_LOCAL_LEADER"),
                new SimpleGrantedAuthority("ROLE_USER"));}
        else {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
