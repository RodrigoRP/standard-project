package com.rodrigorp.standardprojectapi.service;

import java.util.List;

public interface GenericService<E, M> {

    E save(E entity);

    E findById(M id);

    List<E> findAll();

 /*   List<E> save(List<E> entities);

    void deleteById(M id);





    Page<E> findAll(Pageable pageable);

    E update(E entity, I id);*/
}