package com.mjs.demo.contacts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjs.demo.contacts.ContactAddress;

public interface ContactAddressRepository extends JpaRepository<ContactAddress, Integer> {

  public Optional<ContactAddress> findBySysidAddress(int _sysidAddress);
  
  public Optional<ContactAddress> findByContact_Id(int _sysidContact);
  
  public void deleteByContact_Id(int _sysidContact);
  
  public void deleteBySysidAddress(int _sysidAddress);
}
