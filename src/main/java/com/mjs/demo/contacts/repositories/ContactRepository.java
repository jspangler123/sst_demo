package com.mjs.demo.contacts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjs.demo.contacts.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
