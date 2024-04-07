package com.autenticacao.petshop.service;

import com.autenticacao.petshop.entity.user.User;
import com.autenticacao.petshop.exception.ResourceAlreadyExistsException;
import com.autenticacao.petshop.exception.ResourceNotFoundException;
import com.autenticacao.petshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IService<User> {

    private final UserRepository repository;
    ModelMapper mapper;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> optional = repository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", id));
    }

    @Override
    public User save(User user) {
        if (repository.findByEmail(user.getEmail()) != null){
            throw new ResourceAlreadyExistsException("Esse usuário já existe");
        }
        return repository.save(user);
    }

    @Override
    public User update(User newUser, Long id) {
        if (repository.findByEmail(newUser.getEmail()) != null){
            throw new ResourceAlreadyExistsException("Esse usuário já existe");
        }
        User user = findById(id);
        newUser.setId(user.getId());
        mapper.map(newUser,user);
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
