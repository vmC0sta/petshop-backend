package com.autenticacao.petshop.entity.user;

import com.autenticacao.petshop.entity.client.Client;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToOne
    private Client client;
}
