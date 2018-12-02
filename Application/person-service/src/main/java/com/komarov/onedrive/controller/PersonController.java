package com.komarov.onedrive.controller;

import com.komarov.onedrive.dao.entity.Person;
import com.komarov.onedrive.dao.entity.Role;
import com.komarov.onedrive.service.PersonService;
import com.komarov.onedrive.service.dto.entity.PersonDTO;
import com.komarov.onedrive.service.security.CustomUser;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);
  private PersonService personService;

  public long getPersonId() {
    CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    return customUser == null ? 0 : customUser.getId();
  }

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PutMapping("/users")
  public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
    personDTO.setId(getPersonId());
    LOG.info("Update person: " + personDTO);
    return ResponseEntity.ok(personService.update(personDTO));
  }


  @PostMapping(value = "/registration")
  public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) {
    return new ResponseEntity(personService.register(personDTO, Role.USER), HttpStatus.CREATED);
  }

  @GetMapping("/users")
  public ResponseEntity<PersonDTO> getPerson() {
    return ResponseEntity.ok(personService.find(getPersonId()));
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
