package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResponseDTO {

    private Data data;
}
