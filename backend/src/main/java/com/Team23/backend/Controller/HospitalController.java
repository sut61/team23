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
public class HospitalController {
    @Autowired
    private final HospitalRepository hospitalRepository;
    @Autowired
    private AffiliationRepository affiliationRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private TypeHospitalRepository typeHospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/listhospital")
    public Collection<Hospital> hospital() {
        return hospitalRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/addhospital/{branceNine}/{branceFive}/{hospitalName}/{affiliationName}/{typeName}/{hospitalAddress}/{provinceName}/{hospitalPostcode}/{hospitalPhone}")
    public Hospital newHospital(@PathVariable Long branceNine, @PathVariable Long branceFive,
            @PathVariable String hospitalName, @PathVariable String affiliationName, @PathVariable String typeName,
            @PathVariable String hospitalAddress, @PathVariable String provinceName,
            @PathVariable Long hospitalPostcode, @PathVariable String hospitalPhone) {
        Hospital hos = new Hospital();
        hos.setBranceNine(branceNine);
        hos.setBranceFive(branceFive);
        hos.setHospitalName(hospitalName);
        hos.setHospitalAddress(hospitalAddress);
        hos.setHospitalPostcode(hospitalPostcode);
        hos.setHospitalPhone(hospitalPhone);

        Affiliation a = affiliationRepository.findByAffiliationName(affiliationName);
        hos.setAffiliationName(a);

        Province p = provinceRepository.findByProvinceName(provinceName);
        hos.setProvinceName(p);

        TypeHospital t = typeHospitalRepository.findByTypeName(typeName);
        hos.setTypeName(t);

        return hospitalRepository.save(hos);

    }

}