package com.komarov.onedrive.dto;

import java.io.Serializable;
import java.util.Objects;

public class ErrorDTO implements Serializable {
  private String title;
  private String message;

  public ErrorDTO(String title, String message) {
    this.title = title;
    this.message = message;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDTO errorDTO = (ErrorDTO) o;
    return Objects.equals(title, errorDTO.title) &&
        Objects.equals(message, errorDTO.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, message);
  }
}

