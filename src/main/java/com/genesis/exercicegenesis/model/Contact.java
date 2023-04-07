package com.genesis.exercicegenesis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String adresse;
    private String nom;
    private String prenom;
    @OneToMany
    private List<Entreprise> entrepriseList;
}
