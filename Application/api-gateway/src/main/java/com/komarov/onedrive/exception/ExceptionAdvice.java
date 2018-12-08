package com.komarov.onedrive.exception;

import com.komarov.onedrive.exception.LogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class ExceptionAdvice {
  @ExceptionHandler(HttpStatusCodeException.class)
  public ResponseEntity handleHttpStatusCodeException(HttpStatusCodeException e){
    return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
  }

  @ExceptionHandler(LogicException.class)
  public ResponseEntity handleLogicException(LogicException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }
}
