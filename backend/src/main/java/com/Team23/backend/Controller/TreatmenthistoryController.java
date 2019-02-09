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
    @Autowired private GoldcardRepository goldcardRepository;
    @Autowired private DrugRepository drugRepository;
    @Autowired private DiseaseRepository diseaseRepository;

    public TreatmenthistoryController(TreatmenthistoryRepository treatmenthistoryRepository) {
        this.treatmenthistoryRepository = treatmenthistoryRepository;
        this.goldcardRepository = goldcardRepository;
        this.drugRepository = drugRepository;
        this.diseaseRepository = diseaseRepository;
    }

    @GetMapping("/Treatmenthistory")
    public Collection<Treatmenthistory> Treatmenthistory() {
        return treatmenthistoryRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Treatmenthistory/{goldcardName}/{diseaseName}/{drugName}/{code}/{treatDate}")
    public Treatmenthistory newTreatmenthistory(@PathVariable String goldcardName,
                                                @PathVariable String diseaseName,
                                                @PathVariable String drugName,
                                                @PathVariable String code,
                                                @PathVariable String treatDate
    ){
        Treatmenthistory newTreat = new Treatmenthistory();
        Goldcard goldcardid = new Goldcard();
        Drug drugid = new Drug();
        Disease diseaseid = new Disease();

        newTreat.setCode(code);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate tdate = LocalDate.parse(treatDate,formatter);
        newTreat.setTreatDate(tdate);
        goldcardid = goldcardRepository.findByGoldcardName(goldcardName);
        newTreat.setGoldcards(goldcardid);

        drugid = drugRepository.findByDrugName(drugName);
        newTreat.setDrugs(drugid);

        diseaseid = diseaseRepository.findByDiseaseName(diseaseName);
        newTreat.setDiseases(diseaseid);

        return treatmenthistoryRepository.save(newTreat);
    }
}
