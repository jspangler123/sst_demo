package com.mjs.demo.contacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="sysid_contact")  
  private Integer           id  = null;
  
  @Column(name="email")
  private String            email;
  

  /**
   * No arg constructor for serialization
   */
  public Contact(){
  }
    
  /**
   * Constructor
   * @param _email
   */
  public Contact( String _email){
    this.email    = _email;
  }
  
  public Integer getId(){
    return id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
}
