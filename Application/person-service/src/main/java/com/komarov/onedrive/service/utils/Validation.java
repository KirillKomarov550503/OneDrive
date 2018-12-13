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

  public static void validateEmail(String email) throws LogicException {
    Pattern pattern = Pattern.compile("^[0-9a-z.]{6,30}@[a-z]{3,10}.[a-z]{2,3}$");
    if (!pattern.matcher(email).matches()) {
      String error = "Wrong input format of email";
      LOG.error(error);
      throw new LogicException(error);
    }
  }

  public static void validatePassword(String password) throws LogicException {
    Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{6,20}$");
    if (!pattern.matcher(password).matches()) {
      String error = "Wrong input format of password";
      LOG.error(error);
      throw new LogicException(error);
    }
  }
}
