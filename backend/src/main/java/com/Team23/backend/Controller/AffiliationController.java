package com.Team23.backend.Controller;

import com.Team23.backend.Repository.*;
import com.Team23.backend.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AffiliationController {
    @Autowired private final AffiliationRepository affiliationRepository;

    public AffiliationController(AffiliationRepository affiliationRepository){ this.affiliationRepository = affiliationRepository; }


        @GetMapping("/listaffiliation")
    public Collection<Affiliation> affiliation(){
        return affiliationRepository.findAll().stream().collect(Collectors.toList()); }


        @PostMapping("/addaffiliation/{affiliationName}")
    public Affiliation newAffiliation(@PathVariable String affiliationName){
        Affiliation aff = new Affiliation();
        aff.setAffiliationName(affiliationName);
        return affiliationRepository.save(aff);
    }

}