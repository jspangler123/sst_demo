package com.mjs.demo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mjs.demo.contacts.ContactDTO;
import com.mjs.demo.contacts.ContactsService;
import com.mjs.demo.exceptions.SystemException;
import com.mjs.demo.exceptions.WebServiceException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactsController {
  private static final String FQN = ContactsController.class.getName();
  private static final String SN  = ContactsController.class.getSimpleName();
  private static final Logger log = LoggerFactory.getLogger(FQN);
  
  @Autowired
  public ContactsService  service;

  /**
   * Get all contacts
   * @return
   */
  @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value="Get All Contacts",notes="Retrieve all contacts within the database.")
  public List<ContactDTO> getAllContacts(){
    log.trace("IN {}::getAllContacts",SN);
    try {
      return service.getAllContacts();
    }
    catch(WebServiceException e){
      log.error("Service Exception",e);
      throw e;
    }
    catch(Exception e){
      log.error("Unexpected Error",e);
      throw new SystemException("Internal service exception: " + e.getMessage());
    }
    finally {
      log.trace("OUT {}::getAllContacts",SN);  
    }
  }
  
  /**
   * Create a new Contact in the system
   * @param _contact
   * @return
   */
  @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value="Create Contact", notes="Create a new contact within the system.")
  @ApiResponses({
    @ApiResponse(code=200, message="Contact successfully created."),
    @ApiResponse(code=409, message="Invalid Contact Information submitted. Please correct the submission and retry."),
    @ApiResponse(code=500, message="System Exception.")
  })
  @ResponseBody
  public ContactDTO createContact(@RequestBody ContactDTO _contact){
    log.trace("IN {}::createContact",SN);
    try {
      return service.saveContact(_contact);
    }
    catch(WebServiceException e){
      log.error("Service Exception",e);
      throw e;
    }
    catch(Exception e){
      log.error("Unexpected Error",e);
      throw new SystemException("Internal service exception: " + e.getMessage());
    }
    finally {
      log.trace("OUT {}::createContact",SN);  
    }
  }
  
  /**
   * Update the specified contact
   * @param _sysid
   * @param _contact
   * @return
   */
  @PutMapping(path="/{sysid}",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value="Update Contact", notes="Update an existing contact within the system.")
  @ApiResponses({
    @ApiResponse(code=200, message="Contact successfully updated."),
    @ApiResponse(code=409, message="Invalid Contact Information submitted. Please correct the submission and retry."),
    @ApiResponse(code=500, message="System Exception.")
  })
  @ResponseBody
  public ContactDTO updateContact(@PathVariable("sysid") int _sysid, @RequestBody ContactDTO _contact){
    log.trace("IN {}::updateContact",SN);
    try {
      return service.updateContact(_contact);
    }
    catch(WebServiceException e){
      log.error("Service Exception",e);
      throw e;
    }
    catch(Exception e){
      log.error("Unexpected Error",e);
      throw new SystemException("Internal service exception: " + e.getMessage());
    }
    finally {
      log.trace("OUT {}::updateContact",SN);  
    }
  }
  
  /**
   * Find the contact by SYSID
   * @param _sysid
   * @return
   */
  @GetMapping(path="/{sysid}", produces=MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value="Find Contact", notes="Find the Contact associated with the given SYSID value.")
  @ApiResponses({
    @ApiResponse(code=200, message="Contact found."),
    @ApiResponse(code=404, message="Invalid SYSID. No contact found with the given ID value."),
    @ApiResponse(code=500, message="System Exception.")
  })
  @ResponseBody
  public ContactDTO findContact(@PathVariable("sysid") int _sysid){
    log.trace("IN {}::findContact",SN);
    try {
      return service.findContact(_sysid);
    }
    catch(WebServiceException e){
      log.error("Service Exception",e);
      throw e;
    }
    catch(Exception e){
      log.error("Unexpected Error",e);
      throw new SystemException("Internal service exception: " + e.getMessage());
    }
    finally {
      log.trace("OUT {}::findContact",SN);  
    }
  }
  
  /**
   * Remove the contact identified by SYSID
   * @param _sysid
   */
  @DeleteMapping(path="/{sysid}",produces=MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value="Remove Contact", notes="Find the Contact associated with the given SYSID value and remove from the system.")
  @ApiResponses({
    @ApiResponse(code=200, message="Contact removed."),
    @ApiResponse(code=404, message="Invalid SYSID. No contact found with the given ID value."),
    @ApiResponse(code=500, message="System Exception.")
  })
  public void removeContact(@PathVariable("sysid") int _sysid){
    log.trace("IN {}::removeContact",SN);
    try {
      service.removeContact(_sysid);
    }
    catch(WebServiceException e){
      log.error("Service Exception",e);
      throw e;
    }
    catch(Exception e){
      log.error("Unexpected Error",e);
      throw new SystemException("Internal service exception: " + e.getMessage());
    }
    finally {
      log.trace("OUT {}::removeContact",SN);  
    }
  }
}
