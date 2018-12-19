package com.Team23.backend.Controller;

import com.Team23.backend.Repository.*;
import com.Team23.backend.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class gHostpitalController {
    @Autowired
    private final gHostpitalRepository ghostpitalRepository;

    public gHostpitalController(gHostpitalRepository ghostpitalRepository) {
        this.ghostpitalRepository = ghostpitalRepository;
    }

    @GetMapping("/Hostpital")
    public Collection<gHostpital> GHostpital() {
        return ghostpitalRepository.findAll().stream().collect(Collectors.toList());
    }

}
