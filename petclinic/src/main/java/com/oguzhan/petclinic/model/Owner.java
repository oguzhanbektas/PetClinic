package com.oguzhan.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Owner {

    private long id;
    private String firstName;
    private String lastName;

    @JsonIgnore
    private Set<Pet> pets = new HashSet<>();
}
