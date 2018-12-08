package com.komarov.onedrive.services.configuration;

import com.komarov.onedrive.services.exception.ErrorDTO;
import com.komarov.onedrive.services.exception.NotFoundException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileExceptionHandler {

  @ExceptionHandler(IOException.class)
  public ResponseEntity<ErrorDTO> getIOException(IOException e) {
    return new ResponseEntity(new ErrorDTO("IOException", e.getMessage()), HttpStatus.OK);
  }

  public ResponseEntity<ErrorDTO> getNotFoundException(NotFoundException e) {
    return new ResponseEntity(new ErrorDTO("NotFoundException", e.getMessage()),
        HttpStatus.NOT_FOUND);
  }
}
