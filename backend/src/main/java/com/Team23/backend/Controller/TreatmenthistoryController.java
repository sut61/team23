package com.Team23.backend.Controller;

import com.Team23.backend.Repository.*;
import com.Team23.backend.Entity.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import java.time.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TreatmenthistoryController {
    @Autowired private final TreatmenthistoryRepository treatmenthistoryRepository;
    @Autowired private RightRegistrationRepository rightRegistrationRepository;
    @Autowired private DrugRepository drugRepository;
    @Autowired private DiseaseRepository diseaseRepository;

    public TreatmenthistoryController(TreatmenthistoryRepository treatmenthistoryRepository) {
        this.treatmenthistoryRepository = treatmenthistoryRepository;
        this.rightRegistrationRepository = rightRegistrationRepository;
        this.drugRepository = drugRepository;
        this.diseaseRepository = diseaseRepository;
    }

    @GetMapping("/Treatmenthistory")
    public Collection<Treatmenthistory> Treatmenthistory() {
        return treatmenthistoryRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Treatmenthistory/{username}/{diseaseName}/{drugName}/{treatDate}")
    public Treatmenthistory newTreatmenthistory(@PathVariable String username,
                                                @PathVariable String diseaseName,
                                                @PathVariable String drugName,
                                                @PathVariable String treatDate
    ){
        Treatmenthistory newTreat = new Treatmenthistory();
        RightRegistration goldcardid = new RightRegistration();
        Drug drugid = new Drug();
        Disease diseaseid = new Disease();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate tdate = LocalDate.parse(treatDate,formatter);
        newTreat.setTreatDate(tdate);
        goldcardid = rightRegistrationRepository.findByUsername(username);
        newTreat.setRightRegistration(goldcardid);

        drugid = drugRepository.findByDrugName(drugName);
        newTreat.setDrugs(drugid);

        diseaseid = diseaseRepository.findByDiseaseName(diseaseName);
        newTreat.setDiseases(diseaseid);

        return treatmenthistoryRepository.save(newTreat);
    }
}
