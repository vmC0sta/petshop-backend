package com.autenticacao.petshop.entity.user;

import com.autenticacao.petshop.entity.address.Address;
import com.autenticacao.petshop.entity.client.Client;
import lombok.Data;

@Data
public class CreateUserRequest {
    private User user;
    private Client client;
    private Address address;

}