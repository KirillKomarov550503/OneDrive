package com.komarov.onedrive.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

  @RequestMapping("/")
  public ModelAndView getHomePage(ModelAndView modelAndView) {
    modelAndView.setViewName("index");
    return modelAndView;
  }
}

