package com.oguzhan.petclinic.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Owner {

    private long id;
    private String firstName;
    private String lastName;

    private Set<Pet> pets = new HashSet<>();
}
