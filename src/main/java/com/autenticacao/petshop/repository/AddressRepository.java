package com.autenticacao.petshop.repository;

import com.autenticacao.petshop.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
