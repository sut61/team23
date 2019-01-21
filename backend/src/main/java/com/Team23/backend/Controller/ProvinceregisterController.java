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
public class ProvinceregisterController {
    @Autowired
    private final ProvinceregisterRepository provinceregisterRepository;

    public ProvinceregisterController(ProvinceregisterRepository provinceregisterRepository) {
        this.provinceregisterRepository = provinceregisterRepository;
    }

    @GetMapping("/listprovince")
    public Collection<Provinceregister> provinceregister() {
        return provinceregisterRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/addprovince/{provinceName}")
    public Provinceregister newProvince(@PathVariable String provinceName) {
        Provinceregister pro = new Provinceregister();
        pro.setProvinceName(provinceName);
        return provinceregisterRepository.save(pro);
    }

}