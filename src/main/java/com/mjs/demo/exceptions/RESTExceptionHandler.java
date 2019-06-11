package com.mjs.demo.exceptions;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class RESTExceptionHandler extends ExceptionHandlerExceptionResolver{
  private static final Logger log   = LoggerFactory.getLogger(RESTExceptionHandler.class);

  
  @ExceptionHandler({InvalidDataException.class})
  public ResponseEntity<String> handleInvalidDataException(InvalidDataException e){
    return error(HttpStatus.CONFLICT,e);
  }
  
  @ExceptionHandler({NotFoundException.class})
  public ResponseEntity<String> handleNotFoundException(NotFoundException e){
    return error(NOT_FOUND,e);
  }
  
  @ExceptionHandler({SystemException.class})
  public ResponseEntity<String> handleSystemException(SystemException e){
    return error(INTERNAL_SERVER_ERROR,e);
  }
  
  private ResponseEntity<String> error(HttpStatus status, Exception e) {
      log.error("Exception : ", e);
      return ResponseEntity.status(status).body(e.getMessage());
  }
}
