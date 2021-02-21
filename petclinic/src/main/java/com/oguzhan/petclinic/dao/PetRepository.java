package com.oguzhan.petclinic.dao;

import com.oguzhan.petclinic.model.Pet;

import java.util.List;

public interface PetRepository {

    List<Pet> findAll();

    Pet findById(Long id);

    List<Pet> findByName(String name);

    void create(Pet Pet);

    Pet update(Pet pet);

    void delete(Long id);

    void deleteByOwnerId(Long ownerId);
}
