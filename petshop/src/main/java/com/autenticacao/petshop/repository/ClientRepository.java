package com.autenticacao.petshop.repository;

import com.autenticacao.petshop.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
        public Client findByName(String name);
        public Client findByCpf(String cpf);
        public Client findByPhone(String phone);
}
