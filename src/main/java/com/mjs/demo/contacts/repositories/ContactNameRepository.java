package com.mjs.demo.contacts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjs.demo.contacts.ContactName;

public interface ContactNameRepository extends JpaRepository<ContactName, Integer> {

  public Optional<ContactName> findBySysidName(Integer _sysidName);
  
  public Optional<ContactName> findByContact_Id(int _sysidContact);
  
  public void deleteBySysidName(int _sysidName);
  
  public void deleteByContact_Id(int _sysidContact);
}
