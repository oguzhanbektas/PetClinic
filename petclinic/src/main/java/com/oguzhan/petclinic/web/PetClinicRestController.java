package com.oguzhan.petclinic.web;

import com.oguzhan.petclinic.exception.InternalServerException;
import com.oguzhan.petclinic.exception.OwnerNotFoundException;
import com.oguzhan.petclinic.model.Owner;
import com.oguzhan.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @RequestMapping(method = RequestMethod.POST, value = "/owner")
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
        try {
            petClinicService.createOwner(owner);
            Long id = owner.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable Long id, @RequestBody Owner ownerRequest) {
        try {
            Owner owner = petClinicService.findOwner(id);
            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());
            petClinicService.updateOwner(owner);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
        try {
            petClinicService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }
}
