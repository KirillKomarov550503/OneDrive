package com.komarov.onedrive.controller;

import com.komarov.onedrive.dto.PersonDTO;
import com.komarov.onedrive.feign.FileClient;
import com.komarov.onedrive.feign.PersonClient;
import com.komarov.onedrive.security.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PersonController extends BaseController {

  private final PersonClient personClient;
  private static final Logger LOG = LoggerFactory.getLogger(FileClient.class);

  @Autowired
  public PersonController(PersonClient personClient) {
    this.personClient = personClient;
  }


  @GetMapping("/registration")
  public ModelAndView getRegistrationPage() {
    return new ModelAndView("registration", "personDTO", new PersonDTO());
  }

  @PostMapping("/registration")
  public ModelAndView registration(@ModelAttribute("personDTO") PersonDTO personDTO,
      BindingResult bindingResult,
      ModelAndView modelAndView) {
    LOG.info("Register new user");

    modelAndView.addObject("fileList", null);
    modelAndView.setViewName("look");
    modelAndView.addObject("attribute", "redirectWithRedirectPrefix");
    modelAndView.setViewName("redirect:/one-drive/users/files");
    personClient.savePerson(personDTO).getBody();

    return modelAndView;
  }

  @GetMapping("/login")
  public ModelAndView login(ModelAndView modelAndView){
    if(Role.USER.equals(getCustomUser().getRole())){
      modelAndView.addObject("attribute", "redirectWithRedirectPrefix");
      modelAndView.setViewName("redirect:/one-drive/users/files");
      return modelAndView;
    } else {
      modelAndView.addObject("attribute", "redirectWithRedirectPrefix");
      modelAndView.setViewName("redirect:/one-drive/statistics");
      return modelAndView;
    }
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
