package com.autenticacao.petshop.infra.security;

import com.autenticacao.petshop.entity.client.Client;
import com.autenticacao.petshop.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = repository.findByEmail(username);
        return  new org.springframework.security.core.userdetails.User(client.getEmail(), client.getPassword(), new ArrayList<>());
    }
}
