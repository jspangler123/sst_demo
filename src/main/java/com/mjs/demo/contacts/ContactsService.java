package com.mjs.demo.contacts;

import java.util.List;

import com.mjs.demo.exceptions.InvalidDataException;
import com.mjs.demo.exceptions.NotFoundException;
import com.mjs.demo.exceptions.SystemException;

public interface ContactsService {
  
  /**
   * Get all contacts in the service
   * @return
   */
  public List<ContactDTO> getAllContacts() throws SystemException;
  
  /**
   * Find the contact value assocaited with the given SYSID. If no contact information is found, 
   * throw a NOT FOUND exception
   * @param _sysid
   */
  public ContactDTO findContact(int _sysid) throws NotFoundException, SystemException;
  
  /**
   * Save the given contact information. If the contact exists, overwrite.
   * @param _contact
   */
  public ContactDTO saveContact(ContactDTO _contact) throws InvalidDataException, SystemException;
  
  
  /**
   * Update the contact associated with the given DTO
   * @param _contact
   * @return
   * @throws InvalidDataException
   * @throws SystemException
   */
  public ContactDTO updateContact(ContactDTO _contact) throws InvalidDataException, SystemException;
  
  /**
   * Remove the contact identified by the given SYSID
   * @param _sysid
   */
  public void removeContact(int _sysid) throws NotFoundException,SystemException;
  
}
