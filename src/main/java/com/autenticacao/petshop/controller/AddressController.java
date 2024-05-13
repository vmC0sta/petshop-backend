package com.autenticacao.petshop.controller;

import com.autenticacao.petshop.entity.address.Address;
import com.autenticacao.petshop.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
@CrossOrigin
public class AddressController {

    private final AddressService service;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Address address) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(address));
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Address address){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(address,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
