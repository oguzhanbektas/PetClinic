package com.oguzhan.petclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PetClinicConfiguration {

    private PetclinicProperties petclinicProperties;

    @Autowired
    public PetClinicConfiguration(PetclinicProperties petclinicProperties) {
        this.petclinicProperties = petclinicProperties;
    }

    @PostConstruct
    public void init() {
        System.out.println("Display owners with pets : " + petclinicProperties.isDisplayOwnersWithPets());
    }
}
