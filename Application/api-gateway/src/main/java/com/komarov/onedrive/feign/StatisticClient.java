package com.komarov.onedrive.feign;

import com.komarov.onedrive.dto.PersonDTO;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "statistic-service")
public interface StatisticClient {

  @GetMapping(value = "/statistics/people")
  ResponseEntity<List<PersonDTO>> getAllPeople();

  @GetMapping(value = "/statistics/people/date")
  ResponseEntity<List<PersonDTO>> getPeopleByTimeRegistrationBorder(
      @RequestParam(name = "early") String early, @RequestParam(name = "later") String later);

  @GetMapping(value = "/statistics/files/average")
  ResponseEntity<Double> getAverageSize(
      @RequestParam(name = "personId", required = false) Long personId);

  @GetMapping(value = "/statistics/files/general")
  ResponseEntity<Long> getGeneralSize(
      @RequestParam(name = "personId", required = false) Long personId);
}
