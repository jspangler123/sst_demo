package com.mjs.demo.contacts;

import java.util.List;

public class ContactDTO {

  private Integer            id = null;
  private ContactName        name;
  private ContactAddress     address;
  private String             email;
  private List<ContactPhone> phone;
  
  /**
   * no-arg constructor for serialization
   */
  public ContactDTO(){
    
  }
  
  /**
   * Constructor from existing Contact
   * @param _contact
   * @param _name
   * @param _address
   * @param _phone
   */
  public ContactDTO(Contact _contact, ContactName _name, ContactAddress _address, List<ContactPhone> _phone){
    id      = _contact.getId();
    email   = _contact.getEmail();
    name    = _name;
    address = _address;
    phone   = _phone;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ContactName getName() {
    return name;
  }

  public void setName(ContactName name) {
    this.name = name;
  }

  public ContactAddress getAddress() {
    return address;
  }

  public void setAddress(ContactAddress address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<ContactPhone> getPhone() {
    return phone;
  }

  public void setPhone(List<ContactPhone> phone) {
    this.phone = phone;
  }
}
