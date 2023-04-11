package com.genesis.exercicegenesis.controller;

import com.genesis.exercicegenesis.dao.ContactRepository;
import com.genesis.exercicegenesis.dao.FreelanceRepository;
import com.genesis.exercicegenesis.model.Contact;
import com.genesis.exercicegenesis.model.Freelance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Contact")
public class ContactController {

    @Autowired
    private ContactRepository repositoryE;
    @Autowired
    private  FreelanceRepository repositoryF;
    @PostMapping("/saveFreelance")
    public String saveFreelance(@RequestBody Freelance freelance){
        repositoryF.save(freelance);
        return "Contact Freelance saved ...";
    }
    @PostMapping("/saveEmploye")
    public String saveEmploye(@RequestBody Contact contact){
        repositoryE.save(contact);
        return "Contact Employee saved ...";
    }

    @PutMapping("/updateFreelance")
    public String updateFreelance(@RequestParam("id") int id, @RequestBody Freelance freelance){
        Optional<Freelance> freelanceData = repositoryF.findById(id);

        if (freelanceData.isPresent()) {
            Freelance freelanceTemp = freelanceData.get();
            if (!ObjectUtils.isEmpty(freelance.getAdresse())) freelanceTemp.setAdresse(freelance.getAdresse());
            if (!ObjectUtils.isEmpty(freelance.getNom())) freelanceTemp.setNom(freelance.getNom());
            if (!ObjectUtils.isEmpty(freelance.getPrenom())) freelanceTemp.setPrenom(freelance.getPrenom());
            if (!ObjectUtils.isEmpty(freelance.getNumeroTVA())) freelanceTemp.setNumeroTVA(freelance.getNumeroTVA());
            repositoryF.save(freelanceTemp);
            return "Contact Freelance updated ...";
        } else {
            return "Contact Freelance not found ...";
        }
    }
    @PutMapping("/updateEmploye")
    public String updateEmploye(@RequestParam("id") int id, @RequestBody Contact employe){
        Optional<Contact> employeData = repositoryE.findById(id);

        if (employeData.isPresent()) {
            Contact employeTemp = employeData.get();
            if (!ObjectUtils.isEmpty(employe.getAdresse())) employeTemp.setAdresse(employe.getAdresse());
            if (!ObjectUtils.isEmpty(employe.getNom())) employeTemp.setNom(employe.getNom());
            if (!ObjectUtils.isEmpty(employe.getPrenom())) employeTemp.setPrenom(employe.getPrenom());
            repositoryE.save(employeTemp);
            return "Contact Employee updated ...";
        } else {
            return "Contact Employee not found ...";
        }
    }
    @DeleteMapping
    public String deleteContact(@RequestParam("id") int id){

        Optional<Contact> contactData = repositoryE.findById(id);

        if (contactData.isPresent()) {
            repositoryE.deleteById(id);
            return "Contact deleted ...";
        } else {
            return "Contact not found ...";
        }
    }

    @GetMapping
    public List<Contact> getAll(){
       return repositoryE.findAll();
    }


}
