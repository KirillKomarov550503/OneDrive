package com.komarov.onedrive.controller;

import com.komarov.onedrive.exception.LogicException;
import com.komarov.onedrive.feign.StatisticClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController extends BaseController {

  private final StatisticClient statisticClient;

  @Autowired
  public StatisticController(StatisticClient statisticClient) {
    this.statisticClient = statisticClient;
  }

  @GetMapping(value = "/statistics/users")
  public ResponseEntity getAllPeople(@RequestParam(name = "early", required = false) String early,
      @RequestParam(name = "later", required = false) String later) {
    if (early == null && later == null) {
      return statisticClient.getAllPeople();
    } else if (early == null || later == null) {
      throw new LogicException("Set early and later request parameters");
    } else {
      return statisticClient.getPeopleByTimeRegistrationBorder(early, later);
    }
  }

  @GetMapping("/statistics/files/average")
  public ResponseEntity<Double> getAverageSize(
      @RequestParam(name = "personId", required = false) Long personId) {
    return statisticClient.getAverageSize(personId);
  }

  @GetMapping("/statistics/files/general")
  public ResponseEntity<Long> getGeneralSize(
      @RequestParam(name = "personId", required = false) Long personId) {
    return statisticClient.getGeneralSize(personId);
  }
}
