package com.komarov.onedrive.controller;

import com.komarov.onedrive.exception.LogicException;
import com.komarov.onedrive.feign.StatisticClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StatisticController extends BaseController {

  private final StatisticClient statisticClient;

  @Autowired
  public StatisticController(StatisticClient statisticClient) {
    this.statisticClient = statisticClient;
  }

  @GetMapping(value = "/statistics")
  public ModelAndView getUserStatisticPage(ModelAndView modelAndView) {
    modelAndView.addObject("get", "no");
    modelAndView.setViewName("user_statistic");
    return modelAndView;
  }

  @GetMapping(value = "/statistics/users")
  public ModelAndView getAllPeople(@RequestParam(name = "early", required = false) String early,
      @RequestParam(name = "later", required = false) String later, ModelAndView modelAndView) {
    modelAndView.getModel().put("get", "yes");
    modelAndView.setViewName("user_statistic");
    if ((early == null && later == null) || ("".equals(early) && "".equals(later))) {
      modelAndView.addObject("peopleList", statisticClient.getAllPeople().getBody());
      return modelAndView;
    } else if ((early == null || later == null) || ("".equals(early) || "".equals(later))) {
      throw new LogicException("Set early and later request parameters");
    } else {
      modelAndView.addObject("peopleList",
          statisticClient.getPeopleByTimeRegistrationBorder(early, later).getBody());
      return modelAndView;
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
