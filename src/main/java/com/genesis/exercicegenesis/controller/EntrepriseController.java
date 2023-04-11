package com.genesis.exercicegenesis.controller;

import com.genesis.exercicegenesis.dao.ContactRepository;
import com.genesis.exercicegenesis.dao.EntrepriseRepository;
import com.genesis.exercicegenesis.model.Contact;
import com.genesis.exercicegenesis.model.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Entreprise")
public class EntrepriseController {
    @Autowired
    private EntrepriseRepository repository;
    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    public String saveEntreprise(@RequestBody Entreprise entreprise){
        repository.save(entreprise);
        return "Entreprise saved ...";
    }
    @PutMapping
    public String updateEntreprise(@RequestParam("id") int id, @RequestBody Entreprise entreprise){
        Optional<Entreprise> entrepriseData = repository.findById(id);

        if (entrepriseData.isPresent()) {
            Entreprise entrepriseTemp = entrepriseData.get();
            if (!ObjectUtils.isEmpty(entreprise.getAdresse())) entrepriseTemp.setAdresse(entreprise.getAdresse());
            if (!ObjectUtils.isEmpty(entreprise.getNumeroTVA())) entrepriseTemp.setNumeroTVA(entreprise.getNumeroTVA());
            repository.save(entrepriseTemp);
            return "Entreprise updated ...";
        } else {
            return "Entreprise not found ...";
        }
    }
    @GetMapping("/findEntrepriseByTVA")
    public Optional<Entreprise> findEntrepriseByTVA(@RequestParam("numeroTVA") int numeroTVA){
        return repository.findAll()
                .stream()
                .filter(entreprise -> entreprise.getNumeroTVA()==numeroTVA)
                .findAny();
    }

    @GetMapping
    public List<Entreprise> findAllEntreprise(){
        return repository.findAll();
    }
    @PostMapping("/addContactEntreprise")
    public String addContactEntreprise(@RequestParam("idEntreprise") int id, @RequestParam("idContact") int contact){
        Optional<Entreprise> entrepriseData = repository.findById(id);
        Optional<Contact> contactData = contactRepository.findById(contact);
        if (entrepriseData.isPresent()) {
            if(contactData.isPresent()) {
                contactData.get().getEntrepriseList().add(entrepriseData.get());
                contactRepository.save(contactData.get());
                return "Contact added in Entreprise  ...";
            }
            else{
                return "Contact not found ...";
            }
        } else {
            return "Entreprise not found ...";
        }
    }
}
