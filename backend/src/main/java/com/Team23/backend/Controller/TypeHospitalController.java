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
public class TypeHospitalController {
    @Autowired private final TypeHospitalRepository typeHospitalRepository;

    public TypeHospitalController(TypeHospitalRepository typeHospitalRepository ){
        this.typeHospitalRepository = typeHospitalRepository;
    }


    @GetMapping("/listtype")
    public Collection<TypeHospital> type(){
        return typeHospitalRepository.findAll().stream().collect(Collectors.toList()); }


    @PostMapping("/addtype/{typeName}")
    public  TypeHospital newType (@PathVariable String typeName){
        TypeHospital t = new TypeHospital();
        t.setTypeName(typeName);
        return typeHospitalRepository.save(t);
    }

}