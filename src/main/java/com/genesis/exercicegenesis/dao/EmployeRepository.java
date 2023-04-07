package com.genesis.exercicegenesis.dao;

import com.genesis.exercicegenesis.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe,Integer> {
}
