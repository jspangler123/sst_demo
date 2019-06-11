package com.mjs.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid Data Provided")
public class InvalidDataException extends WebServiceException {
  private static final long serialVersionUID = 6155604094485387221L;

  public InvalidDataException(){
    super(ExceptionType.INVALID_DATA);
  }
  
  public InvalidDataException(String _msg){
    super(ExceptionType.INVALID_DATA,_msg);
  }
}
