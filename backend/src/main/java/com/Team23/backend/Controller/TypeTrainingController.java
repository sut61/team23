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
public class TypeTrainingController {
    @Autowired private final TypeTrainingRepository typeTrainingRepository;

    public TypeTrainingController(TypeTrainingRepository typeTrainingRepository){ this.typeTrainingRepository = typeTrainingRepository; }


        @GetMapping("/listtypetraining")
    public Collection<TypeTraining> typeTraining(){
        return typeTrainingRepository.findAll().stream().collect(Collectors.toList()); }


        @PostMapping("/addtypetraining/{typeTrainingName}")
    public TypeTraining newTypeTraining(@PathVariable String typeTrainingName){
       TypeTraining typeT = new TypeTraining();
       typeT.setTypeTrainingName(typeTrainingName);
       return typeTrainingRepository.save(typeT);

    }

}