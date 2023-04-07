package com.genesis.exercicegenesis.controller;

import com.genesis.exercicegenesis.dao.EmployeRepository;
import com.genesis.exercicegenesis.dao.FreelanceRepository;
import com.genesis.exercicegenesis.model.Employe;
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
    private EmployeRepository repositoryE;
    @Autowired
    private  FreelanceRepository repositoryF;
    @PostMapping("/saveFreelance")
    public String saveFreelance(@RequestBody Freelance freelance){
        repositoryF.save(freelance);
        return "Contact Freelance saved ...";
    }
    @PostMapping("/saveEmploye")
    public String saveEmploye(@RequestBody Employe employe){
        repositoryE.save(employe);
        return "Contact Employee saved ...";
    }

    @PutMapping("/updateFreelance/{id}")
    public String updateFreelance(@PathVariable("id") int id, @RequestBody Freelance freelance){
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
    @PutMapping("/updateEmploye/{id}")
    public String updateEmploye(@PathVariable("id") int id, @RequestBody Employe employe){
        Optional<Employe> employeData = repositoryE.findById(id);

        if (employeData.isPresent()) {
            Employe employeTemp = employeData.get();
            employeTemp.setAdresse(employe.getAdresse());
            employeTemp.setNom(employe.getNom());
            employeTemp.setPrenom(employe.getPrenom());
            repositoryE.save(employeTemp);
            return "Contact Employee updated ...";
        } else {
            return "Contact Employee not found ...";
        }
    }
    @DeleteMapping("/deleteFreelance/{id}")
    public String deleteFreelance(@PathVariable("id") int id){

        Optional<Freelance> FreelanceData = repositoryF.findById(id);

        if (FreelanceData.isPresent()) {
            repositoryF.deleteById(id);
            return "Contact Freelance deleted ...";
        } else {
            return "Contact Freelance not found ...";
        }
    }
    @DeleteMapping("/deleteEmploye/{id}")
    public String deleteEmploye(@PathVariable("id") int id){

        Optional<Employe> employeData = repositoryE.findById(id);

        if (employeData.isPresent()) {
            repositoryE.deleteById(id);
            return "Contact Employee deleted ...";
        } else {
            return "Contact Employee not found ...";
        }
    }
    @GetMapping("/getAllContactEmployee")
    public List<Employe> getAllEmploye(){
       return repositoryE.findAll();
    }
    @GetMapping("/getAllContactFreelance")
    public List<Freelance> getAllFreelance(){
        return repositoryF.findAll();
    }

}
