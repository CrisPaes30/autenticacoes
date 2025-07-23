package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.service.impl;

import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.repository.LcKeycloakErrorCadastroRepository;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests.AutenticaRequest;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.requests.dto.AutenticaRequestDTO;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.service.AutenticaService;
import com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;


import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

import static com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.enuns.KeyEnums.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutenticaServiceImpl implements AutenticaService {

    private final Keycloak keycloak;
    private final LcKeycloakErrorCadastroRepository lcKeycloakErrorCadastroRepository;

    @Override
    public void cadastroUsuarioKeyCloak(AutenticaRequestDTO autenticaRequest) {

        log.info("Realizando cadastro no Keycloak");

        // Criação do usuário
        UserRepresentation user = new UserRepresentation();
        user.setUsername(autenticaRequest.getItem().get(0).getAutenticaRequest().getUsuario());
        user.setEmail(autenticaRequest.getItem().get(0).getAutenticaRequest().getEmail());
        user.setLastName(autenticaRequest.getItem().get(0).getAutenticaRequest().getSobrenome());
        user.setFirstName(autenticaRequest.getItem().get(0).getAutenticaRequest().getNome());
        user.setEnabled(true);

        // Adiciona a senha ao usuário
        CredentialRepresentation passwordCredential = createCredential("password", autenticaRequest.getItem().get(0).getAutenticaRequest().getSenha());
        passwordCredential.setTemporary(false); // Define se a senha será temporária
        user.setCredentials(List.of(passwordCredential));

        Response response;

        // Cria o usuário no realm "lecosBurguer"
        try {
            response = keycloak.realm("lecosBurguer").users().create(user);
        } catch (BusinessException e) {
            log.error(KEY_0002.getCode());
            log.info(KEY_0003.getCode(), e.getMessage());

            try {
                lcKeycloakErrorCadastroRepository.insereUsuario(autenticaRequest.getItem().get(0).getAutenticaRequest().getUsuario(),
                        autenticaRequest.getItem().get(0).getAutenticaRequest().getEmail(), autenticaRequest.getItem().get(0).getAutenticaRequest().getSobrenome(),
                        autenticaRequest.getItem().get(0).getAutenticaRequest().getNome(),
                        autenticaRequest.getItem().get(0).getAutenticaRequest().getRole(), autenticaRequest.getItem().get(0).getAutenticaRequest().getSenha(), "PENDENTE");
            } catch (Exception ex) {
                log.error("Erro ao inserir o cadastro no banco", ex.getMessage());
            }
            throw new BusinessException(KEY_0001.getCode());
        }

        if (response.getStatus() == 201) {
            // Recupera o ID do usuário criado
            String userId = response.getLocation().getPath().replaceAll(".*/(.*)$", "$1");

            // Obtém o ID do cliente (lecosBurguer)
            String clientId = keycloak.realm("lecosBurguer")
                    .clients()
                    .findByClientId("lecosBurguer")
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cliente 'lecosBurguer' não encontrado"))
                    .getId();

            // Recupera a representação da role no nível do cliente
            RoleRepresentation clientRole = keycloak.realm("lecosBurguer")
                    .clients()
                    .get(clientId)
                    .roles()
                    .get(autenticaRequest.getItem().get(0).getAutenticaRequest().getRole()) // Role passada como parâmetro
                    .toRepresentation();

            // Associa a role ao usuário no nível do cliente
            try {
                keycloak.realm("lecosBurguer")
                        .users()
                        .get(userId)
                        .roles()
                        .clientLevel(clientId)
                        .add(List.of(clientRole));

                log.info("Usuário cadastrado e role '{}' associada com sucesso.", autenticaRequest.getItem().get(0).getAutenticaRequest().getRole());
            } catch (Exception e) {
                log.error(KEY_0004.getCode(), autenticaRequest.getItem().get(0).getAutenticaRequest().getRole(), e.getMessage());
                log.info(KEY_0003.getCode(), e.getMessage());
                lcKeycloakErrorCadastroRepository.insereUsuario(autenticaRequest.getItem().get(0).getAutenticaRequest().getUsuario(),
                        autenticaRequest.getItem().get(0).getAutenticaRequest().getEmail(), autenticaRequest.getItem().get(0).getAutenticaRequest().getSobrenome(),
                        autenticaRequest.getItem().get(0).getAutenticaRequest().getNome(),
                        autenticaRequest.getItem().get(0).getAutenticaRequest().getRole(), autenticaRequest.getItem().get(0).getAutenticaRequest().getSenha(), "PENDENTE");
                throw new BusinessException(KEY_0001.getCode());
            }
        } else {
            log.error(KEY_0002.getCode(), response.getStatus());
            log.info(KEY_0003.getCode(), response.getStatus());
            lcKeycloakErrorCadastroRepository.insereUsuario(autenticaRequest.getItem().get(0).getAutenticaRequest().getUsuario(),
                    autenticaRequest.getItem().get(0).getAutenticaRequest().getEmail(), autenticaRequest.getItem().get(0).getAutenticaRequest().getSobrenome(),
                    autenticaRequest.getItem().get(0).getAutenticaRequest().getNome(),
                    autenticaRequest.getItem().get(0).getAutenticaRequest().getRole(), autenticaRequest.getItem().get(0).getAutenticaRequest().getSenha(), "PENDENTE");
            throw new BusinessException(KEY_0001.getCode());
        }
    }

    private CredentialRepresentation createCredential(String type, String value) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(type);
        credential.setValue(value);
        credential.setTemporary(false); // Define se a credencial é temporária
        return credential;
    }

}
