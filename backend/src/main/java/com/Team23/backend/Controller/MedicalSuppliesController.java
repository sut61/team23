package com.Team23.backend.Controller;

import java.util.Collection;
import com.Team23.backend.Entity.*;
import com.Team23.backend.Repository.MedicalInstrumentRepository;
import com.Team23.backend.Repository.MedicalSuppliesRepository;
import com.Team23.backend.Repository.UseabilityRepository;

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
public class MedicalSuppliesController {
    @Autowired  MedicalSuppliesRepository       medicalSuppliesRepository;
    @Autowired  MedicalInstrumentRepository     medicalInstrumentRepository;
    @Autowired  UseabilityRepository            useabilityRepository;
    
    
    @GetMapping("/MedicalSupplies")
    public Collection <MedicalSupplies> MedicalSupplies(){
        return medicalSuppliesRepository.findAll();
    }

    @DeleteMapping("/MedicalSupplies/{medicalsuppliesId}")
    public ResponseEntity <Void> deleteDrug(@PathVariable Long medicalsuppliesId){
        medicalSuppliesRepository.deleteById(medicalsuppliesId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/MedicalInstrument")
    public Collection <MedicalInstrument> MedicalInstrument(){
        return medicalInstrumentRepository.findAll();
    }   
    @GetMapping("/Useability")
    public Collection <Useability> Useability(){
        return useabilityRepository.findAll();
    }


    
    @PostMapping("/MedicalSupplies/{codeNumber}/{modelNumber}/{medicalsuppliesName}/{brandName}/{properties}/{medicalInstrumentName}/{useabilityName}")
    public MedicalSupplies medicalSupplies(@PathVariable String codeNumber,           @PathVariable String modelNumber,
                                           @PathVariable String medicalsuppliesName,  @PathVariable String brandName,
                                           @PathVariable String properties,           @PathVariable String medicalInstrumentName, 
                                           @PathVariable String useabilityName ){

        MedicalSupplies medicalSupplies = new MedicalSupplies();
        MedicalInstrument medicalInstrument = medicalInstrumentRepository.findByMedicalInstrumentName(medicalInstrumentName);
        Useability useability =useabilityRepository.findByuseabilityName(useabilityName);

        medicalSupplies.setCodeNumber(codeNumber);
        medicalSupplies.setModelNumber(modelNumber);
        medicalSupplies.setMedicalsuppliesName(medicalsuppliesName);
        medicalSupplies.setBrandName(brandName);
        medicalSupplies.setProperties(properties);
        medicalSupplies.setMedicalInstrument(medicalInstrument);
        medicalSupplies.setUseability(useability);

        System.out.println(medicalSupplies);
        System.out.println(medicalInstrument);
        System.out.println(useability);
        return  medicalSuppliesRepository.save(medicalSupplies);
    }

    @PutMapping("/MedicalSupplies/{medicalsuppliesId}/{codeNumber}/{modelNumber}/{medicalsuppliesName}/{brandName}/{properties}/{medicalInstrumentName}/{useabilityName}")
    public MedicalSupplies updatemedicalSupplies(@PathVariable String codeNumber,           @PathVariable String modelNumber,
                                           @PathVariable String medicalsuppliesName,  @PathVariable String brandName,
                                           @PathVariable String properties,           @PathVariable String medicalInstrumentName, 
                                           @PathVariable String useabilityName,        @PathVariable Long   medicalsuppliesId ){

        MedicalSupplies medicalSupplies = new MedicalSupplies();
        MedicalInstrument medicalInstrument = medicalInstrumentRepository.findByMedicalInstrumentName(medicalInstrumentName);
        Useability useability =useabilityRepository.findByuseabilityName(useabilityName);
        medicalSupplies.setMedicalsuppliesId(medicalsuppliesId);  
        medicalSupplies.setCodeNumber(codeNumber);
        medicalSupplies.setModelNumber(modelNumber);
        medicalSupplies.setMedicalsuppliesName(medicalsuppliesName);
        medicalSupplies.setBrandName(brandName);
        medicalSupplies.setProperties(properties);
        medicalSupplies.setMedicalInstrument(medicalInstrument);
        medicalSupplies.setUseability(useability);

        System.out.println(medicalSupplies);
        System.out.println(medicalInstrument);
        System.out.println(useability);
        return  medicalSuppliesRepository.save(medicalSupplies);
    }
    



}