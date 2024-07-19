package com.caravana.agendamento.controller;


import com.caravana.agendamento.dto.tokendto.DadosTokenJWT;
import com.caravana.agendamento.dto.usuariosdto.CadastrarUsuarioDTO;
import com.caravana.agendamento.dto.usuariosdto.DadosLoginDTO;
import com.caravana.agendamento.dto.usuariosdto.RetornarDadosUsuarioDTO;
import com.caravana.agendamento.infra.security.TokenService;
import com.caravana.agendamento.model.entities.Usuario;
import com.caravana.agendamento.model.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DadosLoginDTO dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJwt));
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO usuario, UriComponentsBuilder uriBuilder) {
        var salvar = new Usuario(usuario, passwordEncoder);
        usuarioService.save(salvar);
        var uri = uriBuilder.path("/cadastrar/{id}").buildAndExpand(salvar.getId()).toUri();
        return ResponseEntity.created(uri).body(new RetornarDadosUsuarioDTO(salvar));
    }
}
