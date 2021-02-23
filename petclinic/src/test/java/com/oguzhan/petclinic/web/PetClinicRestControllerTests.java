package com.oguzhan.petclinic.web;

import com.oguzhan.petclinic.model.Owner;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetClinicRestControllerTests {

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testGetOwnerById() {
        ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);

        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Oğuz han"));
    }

    @Test
    public void testGetOwnersByLastName() {
        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Bektas", List.class);

        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

        List<Map<String, String>> body = response.getBody();

        List<String> fistNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

        MatcherAssert.assertThat(fistNames, Matchers.containsInAnyOrder("Oğuz han", "Dilara"));
    }

    @Test
    public void testGetOwners() {
        ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
    }

    @Test
    public void testCreateOwner() {
        Owner owner = new Owner();
        owner.setFirstName("Test FirstName");
        owner.setLastName("Test Lastname");

        URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);

        Owner owner2 = restTemplate.getForObject(location, Owner.class);

        MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
        MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));
    }

    @Test
    public void testUpdateOwner() {

        Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/1", Owner.class);

        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Oğuz han"));

        owner.setFirstName("Oğuz han");

        restTemplate.put("http://localhost:8080/rest/owner/1", owner);

        owner = restTemplate.getForObject("http://localhost:8080/rest/owner/1", Owner.class);

        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Oğuz han"));
    }

    @Test
    public void testDeleteOwner() {
        restTemplate.delete("http://localhost:8080/rest/owner/1");

        try {
            restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
            Assert.fail("should have not returned");
        } catch (HttpClientErrorException ex) {
            MatcherAssert.assertThat(ex.getStatusCode().value(), Matchers.equalTo(404));
        }
    }
}
