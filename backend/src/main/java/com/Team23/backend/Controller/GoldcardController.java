package com.Team23.backend.Controller;

import com.Team23.backend.Repository.*;
import com.Team23.backend.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GoldcardController {
    @Autowired
    private final GoldcardRepository goldcardRepository;

    public GoldcardController(GoldcardRepository goldcardRepository) {
        this.goldcardRepository = goldcardRepository;
    }

    @GetMapping("/Goldcard")
    public Collection<Goldcard> Goldcard() {
        return goldcardRepository.findAll().stream().collect(Collectors.toList());
    }

}
