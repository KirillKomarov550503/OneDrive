package com.komarov.onedrive.controller;

import com.komarov.onedrive.security.CustomUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/one-drive")
public class BaseController {

  protected CustomUser getCustomUser() {
    return (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
