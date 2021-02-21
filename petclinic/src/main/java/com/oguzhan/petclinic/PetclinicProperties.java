package com.oguzhan.petclinic;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "petclinic")
public class PetclinicProperties {

    private boolean displayOwnersWithPets = false;
}
