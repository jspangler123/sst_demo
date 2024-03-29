package com.mjs.demo.contacts;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mjs.demo.contacts.repositories.ContactAddressRepository;
import com.mjs.demo.contacts.repositories.ContactNameRepository;
import com.mjs.demo.contacts.repositories.ContactPhoneRepository;
import com.mjs.demo.contacts.repositories.ContactRepository;
import com.mjs.demo.contacts.validators.ContactValidator;
import com.mjs.demo.exceptions.InvalidDataException;
import com.mjs.demo.exceptions.NotFoundException;
import com.mjs.demo.exceptions.SystemException;

@Service
public class ContactServiceImpl implements ContactsService {
  private static final String   FQN = ContactServiceImpl.class.getName();
  private static final String   SN  = ContactServiceImpl.class.getSimpleName();
  private static final Logger   log = LoggerFactory.getLogger(FQN);
  
  @Autowired
  private ContactRepository         contactRepo;
  
  @Autowired
  private ContactAddressRepository  contactAddressRepo;
  
  @Autowired
  private ContactNameRepository     contactNameRepo;
  
  @Autowired
  private ContactPhoneRepository    contactPhoneRepo;
  
  @Autowired
  private ContactValidator          validator;
  
  @Override
  public List<ContactDTO> getAllContacts() throws SystemException {
    log.trace("IN {}::getAllContacts",SN);
    List<Contact>     contacts  = new LinkedList<>();
    List<ContactDTO>  dtos      = new LinkedList<>();
    
    try {
      contacts = contactRepo.findAll();
      for(Contact contact : contacts){
        ContactDTO dto = retrieveContact(contact.getId());
        dtos.add(dto);
      }
      return dtos;
    }
    finally{
      log.trace("OUT {}::getAllContacts");
    }
  }
  
  /*
   * (non-Javadoc)
   * @see com.mjs.demo.contacts.ContactsService#findContact(int)
   */
  @Override
  public ContactDTO findContact(int _sysid) throws NotFoundException, SystemException {
    log.trace("IN {}::findContact",SN);
    Optional<Contact> result;
    ContactDTO        dto;
    try {
      
      // find by SYSID
      result = contactRepo.findById(_sysid);
      if( !result.isPresent()){
        throw new NotFoundException("Could not find a contact matching ID: " + _sysid);
      }
      
      dto = retrieveContact(_sysid);
      return dto;
    }
    finally {
      log.trace("OUT {}::findContact",SN);
    }
  }
  
  /**
   * Retreive the ContactDTO from the given Contact SYSID
   * @param _sysid
   * @return
   */
  private ContactDTO retrieveContact(int _sysid){
    ContactDTO                dto = null;
    Optional<Contact>         optContact;
    Optional<ContactAddress>  optContactAddress;
    Optional<ContactName>     optContactName;
    List<ContactPhone>        phone;
    
    try {
      optContact        = contactRepo.findById(_sysid);
      optContactAddress = contactAddressRepo.findByContact_Id(_sysid);
      optContactName    = contactNameRepo.findByContact_Id(_sysid);
      phone             = contactPhoneRepo.findByContact_Id(_sysid);
      
      dto = new ContactDTO(optContact.get(), optContactName.get(), optContactAddress.get(), phone);  
      
      return dto;
    }
    finally{
      
    }
  }

  @Transactional
  @Override
  public ContactDTO saveContact(ContactDTO _contact) throws InvalidDataException, SystemException {
    log.trace("IN {}::saveContact",SN);
    ContactName     dbName;
    ContactAddress  dbAddress;
    Contact         contact;
    
    try {
      // validate our data
      validator.validateContact(_contact);
      
      // check that contact is new
      if( null != _contact.getId()){
        throw new InvalidDataException("Cannot create new contact with existing contact information.");
      }
      
      // save our contact information
      contact   = contactRepo.save(new Contact( _contact.getEmail()));
      dbName    = _contact.getName();
      dbAddress = _contact.getAddress();
      
      dbName.setContact(contact);
      dbAddress.setContact(contact);
      
      dbName    = contactNameRepo.save(dbName);
      dbAddress = contactAddressRepo.save(dbAddress);
      
      for(ContactPhone cp : _contact.getPhone()){
        cp.setContact(contact);
        contactPhoneRepo.save(cp);
      }
      
      _contact.setId(contact.getId());
      
      return _contact;
    }
    finally {
      log.trace("OUT {}::saveContact",SN);  
    }
  }
  
  @Transactional
  @Override
  public ContactDTO updateContact(ContactDTO _contact) throws InvalidDataException, SystemException {
    log.trace("IN {}::updateContact",SN);
    Optional<Contact> contact;
    int               sysidContact;
    
    try {
      // validate our data
      validator.validateContact(_contact);
      
      // check that contact is pre-existing
      if( null == _contact.getId()){
        throw new InvalidDataException("Cannot update contact without existing contact information.");
      }
      
      // get the contact 
      sysidContact  = _contact.getId();
      contact       = contactRepo.findById(sysidContact);
      
      if( contact.isPresent()){
        Contact         dbValue   = contact.get();
        
        // update contact name
        updateName(sysidContact, _contact.getName());
        
        // update contact address
        updateAddress(sysidContact, _contact.getAddress());
        
        // remove all phone numbers and re-add. This ensures we
        // don't orphan any numbers and that we stay current with
        // values added
        contactPhoneRepo.deleteByContact_Id(dbValue.getId());
        for(ContactPhone cp : _contact.getPhone()){
          cp.setContact(dbValue);
          contactPhoneRepo.save(cp);
        }
        
        dbValue.setEmail(_contact.getEmail());
        
        contactRepo.save(dbValue);
        
        return retrieveContact(sysidContact);
      }

      throw new NotFoundException("Cannot find contact for sysid: " + _contact.getId());
    }
    finally {
      log.trace("OUT {}::updateContact",SN);  
    }
  }
  
  /**
   * Update the name of the contact
   * @param _sysidContact
   * @param _name
   */
  private void updateName(int _sysidContact, ContactName _name){
    log.trace("IN {}::updateName",SN);
    Optional<ContactName> optName;
    ContactName           dbName;
    
    try {
      optName   = contactNameRepo.findByContact_Id(_sysidContact);
      
      if(!optName.isPresent()){
        log.error("Failed to retreive Name associated with contact ID {}",_sysidContact);
        throw new SystemException("Name not found for contact. Update failed.");
      }
      
      dbName    = optName.get();
      
      dbName.setFirst(_name.getFirst());
      dbName.setLast(_name.getLast());
      dbName.setMiddle(_name.getMiddle());
      contactNameRepo.save(dbName);
    }
    finally {
      log.trace("OUT {}::updateName",SN);
    }
  }
  /**
   * UPdate the address portion of the contact
   * @param _sysidContact
   * @param _address
   */
  private void updateAddress(int _sysidContact, ContactAddress _address){
    log.trace("IN {}::updateAddress",SN);
    Optional<ContactAddress>  optAddress;
    ContactAddress            dbAddress;
    
    try{
      optAddress = contactAddressRepo.findByContact_Id(_sysidContact);

      if( !optAddress.isPresent()){
        log.error("Failed to retreive Address associated with contact ID {}",_sysidContact);
        throw new SystemException("Address not found for contact. Update failed.");
      }
      
      dbAddress = optAddress.get();
      dbAddress.setStreet(_address.getStreet());
      dbAddress.setCity(_address.getCity());
      dbAddress.setState(_address.getState());
      dbAddress.setZip(_address.getZip());
      contactAddressRepo.save(dbAddress);
    }
    finally {
      log.trace("OUT {}::updateAddress",SN);
    }
  }
  
  /*
   * (non-Javadoc)
   * @see com.mjs.demo.contacts.ContactsService#removeContact(int)
   */
  @Transactional
  @Override
  public void removeContact(int _sysid) throws NotFoundException, SystemException {
    log.trace("IN {}::findContact",SN);
    Optional<Contact> result;
    
    try {
      // check contact exists
      result = contactRepo.findById(_sysid);
      if( !result.isPresent()){
        throw new NotFoundException("Could not find a contact matching ID: " + _sysid);
      }
      
      // perform deletion
      contactPhoneRepo.deleteByContact_Id(_sysid);
      contactAddressRepo.deleteByContact_Id(_sysid);
      contactNameRepo.deleteByContact_Id(_sysid);
      contactRepo.deleteById(_sysid);
    }
    finally {
      log.trace("OUT {}::findContact",SN);
    }
  }


}
