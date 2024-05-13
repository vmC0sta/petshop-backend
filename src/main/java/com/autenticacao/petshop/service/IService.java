package com.autenticacao.petshop.service;

import java.util.List;

public interface IService<T> {

     List<T> findAll();
     T findById(Long id);
     T save(T t);
     T update(T newt, Long id);
     void delete(Long id);
}
