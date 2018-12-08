package com.komarov.onedrive.feign;

import com.komarov.onedrive.dto.PersonDTO;
import java.util.Date;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("person-service")
public interface PersonClient {

  @PutMapping("/users/{userId}")
  ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO,
      @PathVariable("userId") long userId);


  @PostMapping(value = "/registration")
  ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO);

  @GetMapping("/users/{userId}")
  ResponseEntity<PersonDTO> getPerson(@PathVariable long userId);

  @GetMapping("/statistics/people")
  ResponseEntity<List<PersonDTO>> getAllPeople();

  @GetMapping("/users")
  ResponseEntity<PersonDTO> findPersonByEmail(
      @RequestParam(name = "email", defaultValue = "") String email);

  @GetMapping("/statistics/people/date")
  ResponseEntity<List<PersonDTO>> getPeopleByTimeRegistrationBorder(
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @RequestParam(name = "early") Date early,
      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "later") Date later);
}
