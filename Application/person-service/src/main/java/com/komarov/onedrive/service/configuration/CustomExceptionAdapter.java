package com.komarov.onedrive.service.configuration;

import com.komarov.onedrive.service.exception.LogicException;
import com.komarov.onedrive.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionAdapter {

  @ExceptionHandler(LogicException.class)
  public ResponseEntity getLogicException(LogicException e) {
    return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity getNotFoundException(NotFoundException e){
    return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
  }
}
