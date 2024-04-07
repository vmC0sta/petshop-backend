package com.autenticacao.petshop.entity.client;

import com.autenticacao.petshop.entity.address.Address;
import com.autenticacao.petshop.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String cpf;

    @OneToMany
    private List<Address> adress;

    @Column(unique = true, nullable = false)
    private String phone;

}
