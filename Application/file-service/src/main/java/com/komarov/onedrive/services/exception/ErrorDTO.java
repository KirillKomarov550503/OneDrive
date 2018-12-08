package com.komarov.onedrive.services.exception;

public class ErrorDTO {
  private String title;
  private String message;

  public ErrorDTO(String title, String message) {
    this.title = title;
    this.message = message;
  }
}
