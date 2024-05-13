package com.autenticacao.petshop.repository;

import com.autenticacao.petshop.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
         Client findByName(String name);
         Client findByCpf(String cpf);
         Client findByPhone(String phone);
}
