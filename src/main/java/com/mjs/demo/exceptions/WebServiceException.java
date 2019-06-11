package com.mjs.demo.exceptions;

public abstract class WebServiceException extends RuntimeException {
  private static final long serialVersionUID = WebServiceException.class.hashCode();
  private ExceptionType errorCode;
  private int subErrorCode;
  
  public WebServiceException(ExceptionType _type){
    errorCode = _type;
  }
  
  public WebServiceException(ExceptionType _type, String _msg){
    super(_msg);
    errorCode     = _type;
  }
  
  public WebServiceException(ExceptionType _type, int _subErrorCode){
    errorCode     = _type;
    subErrorCode  = _subErrorCode;
  }
  
  
  public int getErrorCode(){
    return errorCode.getErrorCode();
  }
  
  public int getSubErrorCode(){
    return subErrorCode;
  }
}
