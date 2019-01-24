package com.Team23.backend.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import com.Team23.backend.Entity.Disease;
import com.Team23.backend.Entity.DrugRegistration;
import com.Team23.backend.Entity.*;
import com.Team23.backend.Entity.TypesOfDosageForms;
import com.Team23.backend.Entity.TypesOfDrugs;
import com.Team23.backend.Repository.DiseaseRepository;
import com.Team23.backend.Repository.DrugRegistrationRepository;
import com.Team23.backend.Repository.*;
import com.Team23.backend.Repository.TypesOfDosageFormsRepository;
import com.Team23.backend.Repository.TypesOfDrugsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
@EnableAutoConfiguration
@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class DrugController{
    @Autowired DrugRepository                   drugRepository;
    @Autowired TypesOfDrugsRepository           typesOfDrugsRepository;
    @Autowired DrugRegistrationRepository       drugRegistrationRepository;
    @Autowired TypesOfDosageFormsRepository     typesOfDosageFormsRepository;
    @Autowired DiseaseRepository                diseaseRepository;
    
    @GetMapping("/Drug")
    public Collection <Drug> Drug(){
        return drugRepository.findAll();
    }
    @DeleteMapping("/Drug/{drugId}")
    public ResponseEntity <Void> deleteDrug(@PathVariable Long drugId){
      
        drugRepository.deleteById(drugId);

        return ResponseEntity.noContent().build();
    }
    

    @GetMapping("/TypesOfDrugs")
    public Collection <TypesOfDrugs> typesOfDrugs(){
        return typesOfDrugsRepository.findAll();
    }
    @GetMapping("/TypesOfDrugs/{typesOfDrugsId}")
    public Optional <TypesOfDrugs> typesOfDrugsId(@PathVariable Long typesOfDrugsId){
        return typesOfDrugsRepository.findById(typesOfDrugsId);
    }

    @GetMapping("/DrugRegistration")
    public Collection <DrugRegistration> DrugRegistration (){
        return drugRegistrationRepository.findAll();
    }
    @GetMapping("/DrugRegistration/{drugRegistrationId}")
    public Optional <DrugRegistration> drugRegistrationId(@PathVariable Long drugRegistrationId){
        return  drugRegistrationRepository.findById(drugRegistrationId);
    }

    @GetMapping("/TypesOfDosageForms")
    public Collection<TypesOfDosageForms> TypesOfDosageForms (){
        return typesOfDosageFormsRepository.findAll();
    }
    @GetMapping("/TypesOfDosageForms/{typesOfDosageFormsId}")
    public Optional <TypesOfDosageForms> typesOfDosageFormsId(@PathVariable Long typesOfDosageFormsId){
        return typesOfDosageFormsRepository.findById(typesOfDosageFormsId);
    }

    // @GetMapping("/Disease")
    // public Collection <Disease> Disease (){
    //     return diseaseRepository.findAll();
    // }
    // @GetMapping("/Disease/{diseaseId}")
    // public Optional <Disease> diseaseId(@PathVariable Long diseaseId){
    //     return diseaseRepository.findById(diseaseId);
    // }

 

    @PostMapping("/Drug/{drugName}/{typesOfDrugsName}/{drugRegistrationName}/{typesOfDosageFormsName}/{diseaseName}")
    public Drug drug(   @PathVariable String drugName,          @PathVariable String typesOfDrugsName,
                                @PathVariable String drugRegistrationName,  @PathVariable String typesOfDosageFormsName,
                                @PathVariable String diseaseName
                                ){ 

    Drug                 drug             = new Drug();
    TypesOfDrugs         typesOfDrugs         = typesOfDrugsRepository.findByTypesOfDrugsName(typesOfDrugsName);
    DrugRegistration     drugRegistration     = drugRegistrationRepository.findByDrugRegistrationName(drugRegistrationName);
    TypesOfDosageForms   typesOfDosageForms   = typesOfDosageFormsRepository.findByTypesOfDosageFormsName(typesOfDosageFormsName);
    Disease              disease              = diseaseRepository.findByDiseaseName(diseaseName);      

    drug.setDrugName(drugName);
    drug.setTypesOfDrugs(typesOfDrugs);
    drug.setDrugRegistration(drugRegistration);
    drug.setTypesOfDosageForms(typesOfDosageForms);
    drug.setDisease(disease);
    System.out.println(drug);
    System.out.println(typesOfDrugs);
    System.out.println(drugRegistration);
    System.out.println(typesOfDosageForms);
    System.out.println(disease);

    return drugRepository.save(drug);
    }

    @PutMapping("/Drug/{drugId}/{drugName}/{typesOfDrugsName}/{drugRegistrationName}/{typesOfDosageFormsName}/{diseaseName}")
    public Drug updateDrug(  @PathVariable Long    drugId,                 
                             @PathVariable String drugName,           
                             @PathVariable String  typesOfDrugsName,       @PathVariable String drugRegistrationName,  
                             @PathVariable String  typesOfDosageFormsName, @PathVariable String diseaseName
                                ){ 

    Drug                 drug                 = new Drug();
    TypesOfDrugs         typesOfDrugs         = typesOfDrugsRepository.findByTypesOfDrugsName(typesOfDrugsName);
    DrugRegistration     drugRegistration     = drugRegistrationRepository.findByDrugRegistrationName(drugRegistrationName);
    TypesOfDosageForms   typesOfDosageForms   = typesOfDosageFormsRepository.findByTypesOfDosageFormsName(typesOfDosageFormsName);
    Disease              disease              = diseaseRepository.findByDiseaseName(diseaseName);      
    drug.setDrugId(drugId);                                
    drug.setDrugName(drugName);
    drug.setTypesOfDrugs(typesOfDrugs);
    drug.setDrugRegistration(drugRegistration);
    drug.setTypesOfDosageForms(typesOfDosageForms);
    drug.setDisease(disease);
    System.out.println(drug);
    System.out.println(typesOfDrugs);
    System.out.println(drugRegistration);
    System.out.println(typesOfDosageForms);
    System.out.println(disease);

    return drugRepository.save(drug);
    }

}
