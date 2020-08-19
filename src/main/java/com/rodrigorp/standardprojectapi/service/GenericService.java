package com.rodrigorp.standardprojectapi.service;

public interface GenericService<E, M> {

    E save(E entity);

    E findById(M id);

 /*   List<E> save(List<E> entities);

    void deleteById(M id);



    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    E update(E entity, I id);*/
}