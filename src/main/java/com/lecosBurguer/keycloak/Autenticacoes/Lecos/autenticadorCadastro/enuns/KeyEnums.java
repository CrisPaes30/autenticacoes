package com.lecosBurguer.keycloak.Autenticacoes.Lecos.autenticadorCadastro.enuns;

public enum KeyEnums {

    KEY_0001("KEY-0001"),
    KEY_0002("KEY-0002"),
    KEY_0003("KEY-0003"),
    KEY_0004("KEY-0004"),
    KEY_0005("KEY-0005"),
    KEY_0006("KEY-0006");

    private String code;

    KeyEnums(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
