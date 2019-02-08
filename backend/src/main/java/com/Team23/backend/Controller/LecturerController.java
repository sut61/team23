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
public class LecturerController {
    @Autowired private final LecturerRepository lecturerRepository;

    public LecturerController(LecturerRepository lecturerRepository){ this.lecturerRepository = lecturerRepository; }


        @GetMapping("/listlecturer")
    public Collection<Lecturer> lecturer(){
        return lecturerRepository.findAll().stream().collect(Collectors.toList()); }


        @PostMapping("/addlecturer/{lecturerName}")
    public Lecturer newLecturer(@PathVariable String lecturerName){
        Lecturer lec = new Lecturer();
        lec.setLecturerName(lecturerName);
        return lecturerRepository.save(lec);

    }

}