package com.genesis.exercicegenesis.dao;

import com.genesis.exercicegenesis.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
