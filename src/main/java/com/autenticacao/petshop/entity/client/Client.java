package com.autenticacao.petshop.entity.client;

import com.autenticacao.petshop.entity.address.Address;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String phone;

    @OneToOne
    private Address address;

}
