package com.komarov.onedrive.controller;

import com.komarov.onedrive.dao.entity.Role;
import com.komarov.onedrive.service.PersonService;
import com.komarov.onedrive.service.dto.entity.PersonDTO;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);
  private PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PutMapping("/users/{userId}")
  public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO,
      @PathVariable("userId") long userId) {
    personDTO.setId(userId);
    LOG.info("Update person: " + personDTO);
    return ResponseEntity.ok(personService.update(personDTO));
  }


  @PostMapping(value = "/save")
  public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(personService.register(personDTO, Role.USER));
  }

  @GetMapping("/users")
  public ResponseEntity findPersonByEmail(
      @RequestParam(name = "email", defaultValue = "") String email) {
    return ResponseEntity.status(HttpStatus.OK).body(personService.findByEmail(email));
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<PersonDTO> getPerson(@PathVariable long userId) {
    return ResponseEntity.ok(personService.find(userId));
  }

  @GetMapping("/statistics/people")
  public ResponseEntity<List<PersonDTO>> getAllPeople() {
    return ResponseEntity.ok(personService.findAll());
  }

  @GetMapping("/statistics/people/date")
  public ResponseEntity<List<PersonDTO>> getPeopleByTimeRegistrationBorder(
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @RequestParam(name = "early") Date early,
      @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(name = "later") Date later) {
    return ResponseEntity.ok(personService.findPeopleByDateBetweenEarlyAndLater(early, later));
  }
}
