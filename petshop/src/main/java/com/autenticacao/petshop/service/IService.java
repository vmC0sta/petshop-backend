package com.autenticacao.petshop.service;

import java.util.List;

public interface IService<T> {

    public List<T> findAll();
    public T findById(Long id);
    public T save(T t);
    public T update(T newt, Long id);
    public void delete(Long id);
}
