package com.komarov.onedrive.client;


import com.komarov.onedrive.dto.PersonDTO;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "person-service")
public interface PersonClient {

  @GetMapping("/statistics/people")
  ResponseEntity<List<PersonDTO>> getAllPeople();

  @GetMapping("/statistics/people/date")
  ResponseEntity<List<PersonDTO>> getPeopleByTimeRegistrationBorder(
      @RequestParam(name = "early") String early, @RequestParam(name = "later") String later);
}
