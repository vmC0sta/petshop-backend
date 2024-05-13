package com.autenticacao.petshop.service;

import com.autenticacao.petshop.entity.address.Address;
import com.autenticacao.petshop.entity.client.Client;
import com.autenticacao.petshop.exception.ResourceAlreadyExistsException;
import com.autenticacao.petshop.exception.ResourceNotFoundException;
import com.autenticacao.petshop.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService implements IService<Client> {

    private final ClientRepository repository;
    private final ModelMapper mapper;
    private final AddressService addressService;

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> optional = repository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
    }

    @Transactional
    @Override
    public Client save(Client client) {
        if (repository.findByCpf(client.getCpf()) != null){
            throw new ResourceAlreadyExistsException("Já existe um usuário com esse CPF");
        }
        if (repository.findByPhone(client.getPhone()) != null){
            throw new ResourceAlreadyExistsException("Já existe um usuário com esse número de telefone");
        }
        if (repository.findByEmail(client.getEmail()) != null){
            throw new ResourceAlreadyExistsException("Já existe um usuário com esse email");
        }
        return repository.save(client);
    }

    @Override
    public Client update(Client newClient, Long id) {
        if (repository.findByCpf(newClient.getCpf()) != null){
            throw new ResourceAlreadyExistsException("Já existe um usuário com esse CPF");
        }
        if (repository.findByPhone(newClient.getPhone()) != null){
            throw new ResourceAlreadyExistsException("Já existe um usuário com esse número de telefone");
        }
        if (repository.findByEmail(newClient.getEmail()) != null){
            throw new ResourceAlreadyExistsException("Já existe um usuário com esse email");
        }

        Client client = findById(id);
        newClient.setId(client.getId());
        mapper.map(newClient,client);
        return repository.save(client);

    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Transactional
    public Client createClientWithAddress(Client client, Address address) {
        addressService.save(address);
        client.setAddress(address);
        return save(client);
    }

}
