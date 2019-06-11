package com.mjs.demo.contacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="contact_address")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactAddress {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="sysid_address")  
  @XmlTransient @JsonIgnore
  private Integer sysidAddress = null;
  
  @Column(name="street")
  private String street;
  
  @Column(name="city")
  private String city;
  
  @Column(name="state_province")
  private String state;
  
  @Column(name="postal_code")
  private String zip;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="sysid_contact")
  @XmlTransient @JsonIgnore
  private Contact contact;
  
  /**
   * No arg constructor for serialization
   */
  public ContactAddress(){
    
  }
  
  /**
   * Constructor
   * 
   * @param _street
   * @param _city
   * @param _state
   * @param _zip
   */
  public ContactAddress(Contact _contact, String _street, String _city, String _state, String _zip){
    this.contact  = _contact;
    this.street   = _street;
    this.city     = _city;
    this.state    = _state;
    this.zip      = _zip;
  }

  public Integer getSysidAddress(){
    return sysidAddress;
  }

  public String getStreet(){
    return street;
  }
  
  public void setStreet(String _street){
    this.street = _street;
  }
  
  public String getCity() {
    return city;
  }

  public void setCity(String _city) {
    this.city = _city;
  }

  public String getState() {
    return state;
  }

  public void setState(String _state) {
    this.state = _state;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String _zip) {
    this.zip = _zip;
  }

  public Contact getContact(){
    return contact;
  }
  
  public void setContact(Contact _contact){
    this.contact = _contact;
  }
}