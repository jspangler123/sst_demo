package com.mjs.demo.contacts.validators;

import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mjs.demo.contacts.ContactAddress;
import com.mjs.demo.contacts.ContactDTO;
import com.mjs.demo.contacts.ContactName;
import com.mjs.demo.contacts.ContactPhone;
import com.mjs.demo.exceptions.InvalidDataException;

@Component
public class ContactValidator {
  private static final String SN  = ContactValidator.class.getSimpleName();
  private static final Logger log = LoggerFactory.getLogger(ContactValidator.class);
  
  private static final String   VALID_PHONE_MATCH = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
  
  /**
   * Basic data validation before persisting contact information
   * @param _contact
   */
  public void validateContact(ContactDTO _contact){
    log.trace("IN {}::validateContact",SN);
    
    try {
      //Sanity check
      if ( null == _contact){
        throw new InvalidDataException("Contact Information Not Included.");
      }
      
      validateContactName(_contact);
      
      validateAddress(_contact);
      
      validateContactPhone(_contact);
      
      validateContactEmail(_contact);
    }
    finally{
      log.trace("OUT {}::validateContact",SN);
    }
  }

  /**
   * Validate contact name information
   * @param _contact
   */
  private void validateContactName(ContactDTO _contact) {
    log.trace("IN {}::validateContactName",SN);
    ContactName   name  = _contact.getName();
    
    try {
      // check for name
      if( null == name || 
          null == name.getFirst() || name.getFirst().isEmpty() ||
          null == name.getLast()  || name.getLast().isEmpty()){
        throw new InvalidDataException("Does not meet name requirements");
      }
    }
    finally {
      log.trace("OUT {}::validateContactName",SN);
    }
  }

  /**
   * Validate contact address
   * @param _contact
   */
  private void validateAddress(ContactDTO _contact) {
    log.trace("IN {}::validateAddress",SN);
    ContactAddress      address       = _contact.getAddress();
    
    try {
      // check for address
      if( null == address ||
          null == address.getStreet() || address.getStreet().isEmpty()||
          null == address.getCity() || address.getCity().isEmpty() ||
          null == address.getState() || address.getState().isEmpty() ||
          null == address.getZip() || address.getZip().isEmpty()){
          throw new InvalidDataException("Does not meet address requirements");
      }
    }
    finally {
      log.trace("OUT {}::validateAddress",SN);
    }
  }

  /**
   * Validate contact phone
   * @param _contact
   */
  private void validateContactPhone(ContactDTO _contact) {
    log.trace("IN {}::validateContactPhone",SN);
    List<ContactPhone>  phoneNumbers  = _contact.getPhone();
    
    try {
      if ( null == phoneNumbers || phoneNumbers.isEmpty()){
        throw new InvalidDataException("At least one phone number must be included with phone listing.");
      }
      
      for( ContactPhone phone : phoneNumbers){
        // validate phone number
        if(!phone.getNumber().matches(VALID_PHONE_MATCH)){
          throw new InvalidDataException("Phone Number provided is invalid.");
        }
      }
    }
    finally {
      log.trace("OUT {}::validateContactPhone",SN);
    }
  }

  /**
   * Validate Contact email
   * @param _contact
   */
  private void validateContactEmail(ContactDTO _contact) {
    log.trace("IN {}::validateContactEmail",SN);
    
    try {
      // check if email is present and well formed
      if ( null != _contact.getEmail() && !_contact.getEmail().isEmpty()){
        EmailValidator validator = EmailValidator.getInstance(false);
        if(!validator.isValid(_contact.getEmail())){
          throw new InvalidDataException("Email provided is invalid");
        }
      }
    }
    finally {
      log.trace("OUT {}::validateContactEmail",SN);
    }
  }

}
