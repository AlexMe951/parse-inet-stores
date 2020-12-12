package com.alexme951.parseinetstores.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

  @ExceptionHandler
  public ResponseEntity<?> handleException(Exception ex) {
    return null;
  }
}
