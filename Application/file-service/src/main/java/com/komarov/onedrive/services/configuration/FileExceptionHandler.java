package com.komarov.onedrive.services.configuration;

import com.komarov.onedrive.services.exception.ErrorDTO;
import com.komarov.onedrive.services.exception.ForbiddenException;
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
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorDTO("IOException", e.getMessage()));
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorDTO> getNotFoundException(NotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorDTO("NotFoundException", e.getMessage()));
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ErrorDTO> getForbiddenException(ForbiddenException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorDTO("ForbiddenException", e.getMessage()));
  }
}
