package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.response;

import lombok.Data;

import java.util.List;

@Data
public class Item {
    private String itemId;
    private MessageData data;
    private List<ErrorDetail> error;
}
