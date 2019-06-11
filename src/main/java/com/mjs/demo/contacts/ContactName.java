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
@Table(name="contact_name")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactName {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="sysid_contact_name")  
  @XmlTransient @JsonIgnore
  private Integer sysidName = null;
  
  @Column(name="first_name")
  private String first;
  
  @Column(name="middle_name")
  private String middle;
  
  @Column(name="last_name")
  private String last;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="sysid_contact")
  @XmlTransient @JsonIgnore
  private Contact contact;
  
  /**
   * No arg constructor for serialization
   */
  public ContactName(){
    
  }
  
  public ContactName(Contact _contact, String _first, String _middle, String _last){
    this.contact  = _contact;
    this.first    = _first;
    this.middle   = _middle;
    this.last     = _last;
  }
  

  public Integer getSysidName(){
    return sysidName;
  }
  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getMiddle() {
    return middle;
  }

  public void setMiddle(String middle) {
    this.middle = middle;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }
  
  public Contact getContact(){
    return contact;
  }
  public void setContact(Contact _contact){
    this.contact = _contact;
  }
  
}
