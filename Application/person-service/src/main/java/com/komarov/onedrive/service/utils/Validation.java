package com.komarov.onedrive.service.utils;

import com.komarov.onedrive.service.exception.LogicException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Validation {

  private static final Logger LOG = LoggerFactory.getLogger(Validation.class);

  public static void validateNameAndSurname(String name, String surname) throws LogicException {
    Pattern pattern = Pattern.compile("^[A-Z][a-zA-Z\\-]{2,30}$");
    Matcher nameMatcher = pattern.matcher(name);
    Matcher surnameMatcher = pattern.matcher(surname);
    if (!nameMatcher.matches() || !surnameMatcher.matches()) {
      String error = "Wrong input format of name or surname";
      LOG.info(error);
      throw new LogicException(error);
    }
  }
}
