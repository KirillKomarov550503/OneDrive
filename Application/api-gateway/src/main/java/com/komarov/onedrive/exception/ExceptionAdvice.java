package com.komarov.onedrive.exception;

import com.komarov.onedrive.dto.ErrorDTO;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(HttpStatusCodeException.class)
  public ResponseEntity handleHttpStatusCodeException(HttpStatusCodeException e) {
    return returnErrorDTO(e.getStatusCode(), "HttpStatusCodeException",
        e.getResponseBodyAsString());
  }

  @ExceptionHandler(FeignException.class)
  public ResponseEntity handleFeignException(FeignException e) {
    return ResponseEntity.status(e.status()).body(e.getMessage());
  }

  @ExceptionHandler(LogicException.class)
  public ResponseEntity handleLogicException(LogicException e) {
    return returnErrorDTO(HttpStatus.BAD_REQUEST, "LogicException", e.getMessage());
  }

  private ResponseEntity returnErrorDTO(HttpStatus status, String title, String errorMessage) {
    return ResponseEntity.status(status).body(new ErrorDTO(title, errorMessage));
  }
}
