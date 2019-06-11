package com.mjs.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Server Error")
public class SystemException extends WebServiceException {

  private static final long serialVersionUID = 9179492740966162193L;

  public SystemException(){
    super(ExceptionType.SYSTEM_EXCEPTION);
  }
  
  public SystemException(String _msg){
    super(ExceptionType.SYSTEM_EXCEPTION,_msg);
  }
}
