package com.genesis.exercicegenesis.dao;

import com.genesis.exercicegenesis.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {
}
