package com.autenticacao.petshop.controller;

import com.autenticacao.petshop.dto.LoginRequestDTO;
import com.autenticacao.petshop.dto.ResponseDTO;
import com.autenticacao.petshop.entity.address.Address;
import com.autenticacao.petshop.entity.client.Client;
import com.autenticacao.petshop.entity.client.ClientRole;
import com.autenticacao.petshop.entity.client.CreateClientRequest;
import com.autenticacao.petshop.infra.security.TokenService;
import com.autenticacao.petshop.repository.ClientRepository;
import com.autenticacao.petshop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClientService service;
    private final ClientRepository repository;
    private final PasswordEncoder encoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        Client client = repository.findByEmail(body.email());
        if (encoder.matches(body.password(), client.getPassword())) {
            String token = tokenService.generateToken(client);
            return ResponseEntity.ok(new ResponseDTO(client.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody CreateClientRequest createClientRequest) {

        Client client = createClientRequest.getClient();
        client.setRole(ClientRole.valueOf("USER"));
        Address address = createClientRequest.getAddress();
        client.setPassword(encoder.encode(client.getPassword()));
        service.createClientWithAddress(client, address);

        String token = tokenService.generateToken(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(client.getName(), token));
    }


}
