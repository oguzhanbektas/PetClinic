package com.oguzhan.petclinic.model;

import lombok.Data;

import java.util.Date;

@Data
public class Pet {

    private long id;
    private String name;
    private Date birthDate;

    private Owner owner;
}
