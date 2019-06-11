package com.mjs.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Not Found")
public class NotFoundException extends WebServiceException {
  private static final long serialVersionUID = 3325247551096833626L;

  public NotFoundException(){
    super(ExceptionType.NOT_FOUND);
  }

  public NotFoundException(String _msg){
    super(ExceptionType.NOT_FOUND,_msg);
  }
}
