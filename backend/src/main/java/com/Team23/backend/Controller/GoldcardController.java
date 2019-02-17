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
public class GoldcardController {
    @Autowired private final GoldcardRepository goldcardRepository;
    @Autowired private HospitalRepository hospitalRepository;
    @Autowired private  RightRegistrationRepository rightRegistrationRepository;
    @Autowired private  OfficerRepository officerRepository;

    public GoldcardController(GoldcardRepository goldcardRepository,HospitalRepository hospitalRepository
                                ,RightRegistrationRepository rightRegistrationRepository
                                ,OfficerRepository officerRepository) {
        this.goldcardRepository = goldcardRepository;
        this.hospitalRepository = hospitalRepository;
        this.rightRegistrationRepository = rightRegistrationRepository;
        this.officerRepository = officerRepository;

    }
    @GetMapping("/Goldcard")
    public Collection<Goldcard> Goldcard() {
        return goldcardRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/Goldcard/{detail}/{officerName}/{HospialName}/{username}")
    public  Goldcard newGoldcard(@PathVariable String detail ,@PathVariable String officerName
            ,@PathVariable String HospialName ,@PathVariable String username){
        Goldcard newGoldC = new Goldcard();
        Officer newOfficer = new Officer();
        Hospital newHospital = new Hospital();
        RightRegistration newReg = new RightRegistration();

        newGoldC.setDetail(detail);
        //newGoldC.setAuthorities(Authorities);
        //newGoldC.setHospital(HospialName);
        //newGoldC.setMember(MemberName);

        newOfficer = officerRepository.findByOfficerName(officerName);
        newGoldC.setOfficer(newOfficer);

        newHospital = hospitalRepository.findByHospitalName(HospialName);
        newGoldC.setHospital(newHospital);

//        newMember = memberRepository.findByMemberName(MemberName);
//        newGoldC.setMember(newMember);

        newReg = rightRegistrationRepository.findByUsername(username);
        newGoldC.setRightRegistration(newReg);

        return goldcardRepository.save(newGoldC);
    }

}
