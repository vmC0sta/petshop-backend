package com.autenticacao.petshop.service;

import com.autenticacao.petshop.entity.address.Address;
import com.autenticacao.petshop.exception.ResourceNotFoundException;
import com.autenticacao.petshop.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService implements IService<Address> {

    private final AddressRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Address> findAll() {
        return repository.findAll();
    }

    @Override
    public Address findById(Long id) {
        Optional<Address> optional = repository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException("Endereço", "id",id));
    }
    @Transactional
    @Override
    public Address save(Address address) {
        return repository.save(address);
    }

    @Override
    public Address update(Address newAddress, Long id) {
        Address address = findById(id);
        newAddress.setId(address.getId());
        mapper.map(newAddress,address);
        return repository.save(address);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
