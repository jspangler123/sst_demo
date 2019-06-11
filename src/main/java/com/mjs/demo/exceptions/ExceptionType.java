package com.mjs.demo.exceptions;

public enum ExceptionType {
  NOT_FOUND       (100,"Data requested not found."),
  INVALID_DATA    (200,"Data provided does not meet requirements."),
  SYSTEM_EXCEPTION  (1000,"Internal error occured outside of user or service control.");
  
  private int code;
  private String desc;
  
  private ExceptionType( int _code, String _desc){
    code = _code;
    desc = _desc;
  }
  
  public int getErrorCode(){
    return code;
  }
  
  public String getDesc(){
    return desc;
  }
}
