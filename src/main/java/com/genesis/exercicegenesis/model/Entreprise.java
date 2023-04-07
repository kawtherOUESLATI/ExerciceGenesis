package com.genesis.exercicegenesis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEntreprise;
    private String adresse;
    private int numeroTVA;
    @OneToMany
    private List<Contact> contactList;
}
