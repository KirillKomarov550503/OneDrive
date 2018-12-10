package com.komarov.onedrive.controller;

import com.komarov.onedrive.dto.PersonDTO;
import com.komarov.onedrive.feign.FileClient;
import com.komarov.onedrive.feign.PersonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController extends BaseController {

  private final PersonClient personClient;
  private static final Logger LOG = LoggerFactory.getLogger(FileClient.class);

  @Autowired
  public PersonController(PersonClient personClient) {
    this.personClient = personClient;
  }

  @PostMapping("/registration")
  public ResponseEntity registration(@RequestBody PersonDTO personDTO) {
    return personClient.savePerson(personDTO);
  }

  @PutMapping("/users")
  public ResponseEntity updateUser(@RequestBody PersonDTO personDTO) {
    return personClient.updatePerson(personDTO, getCustomUser().getId());
  }

  @GetMapping("/users")
  public ResponseEntity getUser() {
    return personClient.getPerson(getCustomUser().getId());
  }
}
