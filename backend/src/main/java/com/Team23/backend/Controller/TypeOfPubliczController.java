package com.Team23.backend.Controller;

import com.Team23.backend.Entity.TypeOfPublicz;
import com.Team23.backend.Repository.TypeOfPubliczRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class TypeOfPubliczController {
    @Autowired final TypeOfPubliczRepository typeOfPubliczRepository;

    public TypeOfPubliczController(TypeOfPubliczRepository typeOfPubliczRepository){
        this.typeOfPubliczRepository = typeOfPubliczRepository;
    }
    @GetMapping("/TypeOfPublicz")
    public Collection<TypeOfPublicz> TypeOfPublicz(){
        return typeOfPubliczRepository.findAll().stream().collect(Collectors.toList());
    }
}
