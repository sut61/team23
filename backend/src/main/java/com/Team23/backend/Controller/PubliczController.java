package com.Team23.backend.Controller;

import com.Team23.backend.Entity.*;

import com.Team23.backend.Repository.HospitalRepository;
import com.Team23.backend.Repository.OfficerRepository;
import com.Team23.backend.Repository.PubliczRepository;
import com.Team23.backend.Repository.TypeOfPubliczRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PubliczController {
    @Autowired private  final PubliczRepository publiczRepository;
    @Autowired private HospitalRepository hospitalRepository;
    @Autowired private OfficerRepository officerRepository;
    @Autowired private TypeOfPubliczRepository typeOfPubliczRepository;

    public PubliczController(PubliczRepository publiczRepository , HospitalRepository hospitalRepository , OfficerRepository officerRepository , TypeOfPubliczRepository typeOfPubliczRepository){
        this.publiczRepository = publiczRepository;
        this.hospitalRepository = hospitalRepository;
        this.officerRepository = officerRepository;
        this.typeOfPubliczRepository = typeOfPubliczRepository;
    }

    @GetMapping("/Publicz")
    public Collection<Publicz> publicz(){
        return publiczRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Publicz/{publiczHead}/{PublicizeDetail}/{date_reg}/{officerName}/{hospialName}/{TypeOfPublicz}/{email}/{call}")
    public Publicz newPubz(@PathVariable String publiczHead , @PathVariable String PublicizeDetail , @PathVariable String date_reg , @PathVariable String officerName
            , @PathVariable String hospialName , @PathVariable String TypeOfPublicz , @PathVariable String email , @PathVariable String call
    ){
        Publicz newPub = new Publicz();
        Hospital hospital = new Hospital();
        Officer officer = new Officer();
        com.Team23.backend.Entity.TypeOfPublicz typeOfPublicz = new TypeOfPublicz();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate Kdate = LocalDate.parse(date_reg,formatter);

        newPub.setPubliczHead(publiczHead);
        newPub.setPublicizeDetail(PublicizeDetail);
        newPub.setDate_reg(Kdate);
        newPub.setEmail(email);
        newPub.setCall(call);

        hospital = hospitalRepository.findByHospitalName(hospialName);
        newPub.setHospital(hospital);

        officer = officerRepository.findByOfficerName(officerName);
        newPub.setOfficer(officer);

        typeOfPublicz = typeOfPubliczRepository.findByTypePubName(TypeOfPublicz);
        newPub.setTypeOfPublicz(typeOfPublicz);

        return publiczRepository.save(newPub);
    }
}
