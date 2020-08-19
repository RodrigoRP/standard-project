package com.rodrigorp.standardprojectapi.service;

public interface GenericService<E, M> {
    E save(E entity);

 /*   List<E> save(List<E> entities);

    void deleteById(M id);

    Optional<E> findById(M id);

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    E update(E entity, I id);*/
}