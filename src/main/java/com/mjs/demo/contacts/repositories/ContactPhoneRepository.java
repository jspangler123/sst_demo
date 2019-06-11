package com.mjs.demo.contacts.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjs.demo.contacts.ContactPhone;

public interface ContactPhoneRepository extends JpaRepository<ContactPhone, Integer> {

  public Optional<ContactPhone> findBySysidPhone(Integer _sysidPhone);
  
  public List<ContactPhone> findByContact_Id(int _sysidContact);
  
  public void deleteByContact_Id( int _sysidContact);
  
  public void deleteBySysidPhone( int _sysidPhone);
}
