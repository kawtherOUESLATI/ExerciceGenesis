package com.genesis.exercicegenesis.controller;

import com.genesis.exercicegenesis.dao.EntrepriseRepository;
import com.genesis.exercicegenesis.model.Contact;
import com.genesis.exercicegenesis.model.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Entreprise")
public class EntrepriseController {
    @Autowired
    private EntrepriseRepository repository;

    @PostMapping("/saveEntreprise")
    public String saveEntreprise(@RequestBody Entreprise entreprise){
        repository.save(entreprise);
        return "Entreprise saved ...";
    }
    @PutMapping("/updateEntreprise/{id}")
    public String updateFreelance(@PathVariable("id") int id, @RequestBody Entreprise entreprise){
        Optional<Entreprise> entrepriseData = repository.findById(id);

        if (entrepriseData.isPresent()) {
            Entreprise entrepriseTemp = entrepriseData.get();
            entrepriseTemp.setAdresse(entreprise.getAdresse());
            entrepriseTemp.setNumeroTVA(entreprise.getNumeroTVA());
            repository.save(entrepriseTemp);
            return "Entreprise updated ...";
        } else {
            return "Entreprise not found ...";
        }
    }
    @GetMapping("/findEntrepriseByTVA/{numeroTVA}")
    public Optional<Entreprise> findEntrepriseByTVA(@PathVariable("numeroTVA") int numeroTVA){
        return repository.findAll()
                .stream()
                .filter(entreprise -> entreprise.getNumeroTVA()==numeroTVA)
                .findAny();
    }cd

    @GetMapping("/findAllEntreprise")
    public List<Entreprise> findAllEntreprise(){
        return repository.findAll();
    }
    @PostMapping("/addContactEntreprise/{id}")
    public String addContactEntreprise(@PathVariable("id") int id, @RequestBody Contact contact){
        Optional<Entreprise> entrepriseData = repository.findById(id);

        if (entrepriseData.isPresent()) {
            entrepriseData.get().getContactList().add(contact);
            return "Contact added in Entreprise  ...";
        } else {
            return "Entreprise not found ...";
        }
    }
}
