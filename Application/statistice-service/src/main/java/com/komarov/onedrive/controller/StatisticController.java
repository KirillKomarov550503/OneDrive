package com.komarov.onedrive.controller;

import com.komarov.onedrive.client.FileClient;
import com.komarov.onedrive.client.PersonClient;
import com.komarov.onedrive.dto.PersonDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {

  private final PersonClient personClient;
  private final FileClient fileClient;

  @Autowired
  public StatisticController(PersonClient personClient, FileClient fileClient) {
    this.personClient = personClient;
    this.fileClient = fileClient;
  }

  @GetMapping(value = "/statistics/people")
  public ResponseEntity<List<PersonDTO>> getAllPeople() {
    return personClient.getAllPeople();
  }

  @GetMapping(value = "/statistics/people/date")
  public ResponseEntity<List<PersonDTO>> getPeopleByTimeRegistrationBorder(
      @RequestParam(name = "early") String early,
      @RequestParam(name = "later") String later) {
    return personClient.getPeopleByTimeRegistrationBorder(early, later);
  }

  @GetMapping(value = "/statistics/files/average")
  public ResponseEntity<Double> getAverageSize(
      @RequestParam(name = "personId", required = false) Long personId) {
    return fileClient.getAverageSize(personId);
  }

  @GetMapping(value = "/statistics/files/general")
  public ResponseEntity<Long> getGeneralSize(
      @RequestParam(name = "personId", required = false) Long personId) {
    return fileClient.getGeneralSize(personId);
  }
}
