package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.autenticaController;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests.dto.AutenticaRequestDTO;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AutenticaController {

    @PostMapping
    public ResponseEntity<ResponseDTO> autentica(@RequestBody AutenticaRequestDTO requestDTO);

}
