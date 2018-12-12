package com.komarov.onedrive.controller;

import com.komarov.onedrive.dto.PersonDTO;
import com.komarov.onedrive.dto.StatisticFileDTO;
import com.komarov.onedrive.exception.LogicException;
import com.komarov.onedrive.feign.StatisticClient;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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
      List<PersonDTO> dtos = statisticClient.getAllPeople().getBody();
      modelAndView.addObject("peopleList", dtos);
      return modelAndView;
    } else if ((early == null || later == null) || ("".equals(early) || "".equals(later))) {
      throw new LogicException("Set early and later request parameters");
    } else {
      modelAndView.addObject("peopleList",
          statisticClient.getPeopleByTimeRegistrationBorder(early, later).getBody());
      return modelAndView;
    }
  }

  @GetMapping("/statistics/files")
  public ModelAndView showFileStatisticPage(ModelAndView modelAndView) {
    modelAndView.setViewName("file_statistic");
    List<StatisticFileDTO> dtoList = statisticClient.getAllPeople().getBody()
        .stream()
        .map(personDTO -> new StatisticFileDTO(personDTO.getEmail(),
            statisticClient.getAverageSize(personDTO.getId()).getBody(),
            statisticClient.getGeneralSize(personDTO.getId()).getBody()))
        .collect(Collectors.toList());
    modelAndView.addObject("userFileStatistic", dtoList);
    modelAndView.addObject("generalFileStatistic",
        convertLongBytesToMegaBytes(statisticClient.getGeneralSize(null).getBody()));
    modelAndView.addObject("averageFileStatistic",
        convertDoubleBytesToMegaBytes(statisticClient.getAverageSize(null).getBody()));
    modelAndView.setViewName("file_statistic");
    return modelAndView;
  }

  private Double convertDoubleBytesToMegaBytes(Double number){
    return number / 1024.0 / 1024.0;
  }
  private Double convertLongBytesToMegaBytes(Long number) {
    return number / 1024.0 / 1024.0;
  }
}
