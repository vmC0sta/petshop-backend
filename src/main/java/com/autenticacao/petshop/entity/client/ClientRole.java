package com.autenticacao.petshop.entity.client;



public enum ClientRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    ClientRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
