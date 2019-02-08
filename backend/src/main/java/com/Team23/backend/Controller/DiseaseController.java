package com.Team23.backend.Controller;

import com.Team23.backend.Repository.*;
import com.Team23.backend.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DiseaseController {
    @Autowired private final DiseaseRepository diseaseRepository;
    @Autowired private PeopleDiseaseRepository peopleDiseaseRepository;
    @Autowired private TypeDiseaseRepository typeDiseaseRepository;

    public DiseaseController(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
        this.peopleDiseaseRepository = peopleDiseaseRepository;
        this.typeDiseaseRepository = typeDiseaseRepository;
    }

    @GetMapping("/Disease")
    public Collection<Disease> Disease() {
        return diseaseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Disease/{diseaseName}/{typeDiseaseName}/{populationRate}/{symptom}/{cause}/{remedy}")
    public Disease newDisease(@PathVariable String diseaseName,
                              @PathVariable String typeDiseaseName,
                              @PathVariable String populationRate,
                              @PathVariable String symptom,
                              @PathVariable String cause,
                              @PathVariable String remedy
    ){
        Disease newDis = new Disease();
        TypeDisease typediseaseid = new TypeDisease();
        PeopleDisease peoplediseaseid = new PeopleDisease();

        newDis.setDiseaseName(diseaseName);

        typediseaseid = typeDiseaseRepository.findByTypeDiseaseName(typeDiseaseName);
        newDis.setTypedisease(typediseaseid);

        peoplediseaseid = peopleDiseaseRepository.findByPopulationRate(populationRate);
        newDis.setPeopledisease(peoplediseaseid);

        newDis.setSymptom(symptom);
        newDis.setCause(cause);
        newDis.setRemedy(remedy);

        return diseaseRepository.save(newDis);
    }

}
