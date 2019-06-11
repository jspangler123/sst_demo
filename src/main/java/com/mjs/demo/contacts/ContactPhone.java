package com.mjs.demo.contacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="contact_phone")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactPhone {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="sysid_phone")
  @XmlTransient @JsonIgnore
  private Integer sysidPhone;
  
  @Column(name="number")
  private String number;
  
  @Column(name="phone_type")
  private ContactPhoneType type;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "sysid_contact", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @XmlTransient @JsonIgnore
  private Contact contact;

  /**
   * no-arg constructor for serialization
   */
  public ContactPhone(){
    
  }
  
  /**
   * Constructor
   * @param _number
   * @param _type
   */
  public ContactPhone(String _number, ContactPhoneType _type, Contact _contact){
    this.number   = _number;
    this.type     = _type;
    this.contact  = _contact;
  }
  
  public Integer getSysidPhone(){
    return sysidPhone;
  }
  
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public ContactPhoneType getType() {
    return type;
  }

  public void setType(ContactPhoneType type) {
    this.type = type;
  }
  
  public Contact getContact(){
    return contact;
  }
  
  public void setContact(Contact _contact){
    this.contact = _contact;
  }
}
