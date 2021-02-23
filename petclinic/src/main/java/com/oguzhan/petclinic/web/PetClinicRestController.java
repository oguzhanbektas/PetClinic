package com.oguzhan.petclinic.web;

import com.oguzhan.petclinic.exception.OwnerNotFoundException;
import com.oguzhan.petclinic.model.Owner;
import com.oguzhan.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {

    private PetClinicService petClinicService;

    @Autowired
    public PetClinicRestController(PetClinicService petClinicService) {
        this.petClinicService = petClinicService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        List<Owner> owners = petClinicService.findOwners();
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner")
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastname) {
        List<Owner> owners = petClinicService.findOwners(lastname);
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ResponseEntity<Owner> getOwners(@PathVariable long id) {
        Owner owner = null;
        try {
            owner = petClinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
