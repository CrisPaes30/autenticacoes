package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.autenticaController.impl;


import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.autenticaController.AutenticaController;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests.dto.AutenticaRequestDTO;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.response.ResponseDTO;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.autenticaController.bussines.AutenticaBussines;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/authentication-registration")
public class AutenticaControllerImpl implements AutenticaController {

    private final AutenticaBussines autenticaBussines;

    @Override
    public ResponseEntity<ResponseDTO> autentica(AutenticaRequestDTO autenticaRequest) {

        ResponseDTO response = autenticaBussines.createResponse(autenticaRequest);

        return new ResponseEntity<>(response, hasError(response) ? HttpStatus.MULTI_STATUS: HttpStatus.CREATED);
    }

    private boolean hasError(ResponseDTO response) {
        return response.getData().getItems().stream().anyMatch(item -> item.getError() != null);
    }
}
