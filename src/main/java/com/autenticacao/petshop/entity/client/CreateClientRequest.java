package com.autenticacao.petshop.entity.client;

import com.autenticacao.petshop.entity.address.Address;
import lombok.Data;

@Data
public class CreateClientRequest {
    private Client client;
    private Address address;

}