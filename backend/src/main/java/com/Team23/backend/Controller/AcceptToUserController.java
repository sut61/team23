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
public class AcceptToUserController {
    @Autowired
    private final AcceptToUserRepository acceptToUserRepository;
    @Autowired
    private RightRegistrationRepository rightRegistrationRepository;
    @Autowired
    private OfficerRepository officerRepository;
    @Autowired
    private StatusRepository statusRepository;

    public AcceptToUserController(AcceptToUserRepository acceptToUserRepository, RightRegistrationRepository rightRegistrationRepository
            , OfficerRepository officerRepository, StatusRepository statusRepository) {
        this.acceptToUserRepository = acceptToUserRepository;
        this.statusRepository = statusRepository;
        this.officerRepository = officerRepository;
        this.rightRegistrationRepository = rightRegistrationRepository;
    }

    @GetMapping("/Accepted")
    public Collection<AcceptToUser> Accepted() {
        return acceptToUserRepository.findAll().stream().filter(this::Pass).collect(Collectors.toList());
    }

    private boolean Pass(AcceptToUser acceptToUser) {
        return acceptToUser.getStatus().getStatusName().equals("Pass");
    }

    @GetMapping("/AcceptToUser")
    public Collection<AcceptToUser> AcceptToUser() {
        return acceptToUserRepository.findAll().stream().filter(this::NotPass).collect(Collectors.toList());
    }

    private boolean NotPass(AcceptToUser acceptToUser) {
        return acceptToUser.getStatus().getStatusName().equals("Fail");
    }

    @PostMapping("/AcceptToUser/{acceptdate}/{username}/{comment}/{statusname}/{officername}")
    public AcceptToUser newUser(@PathVariable String acceptdate, @PathVariable String username
                                ,@PathVariable String comment, @PathVariable String statusname, @PathVariable String officername
    ) {
        AcceptToUser accepttouserid = new AcceptToUser();
        RightRegistration rightRegistrationid = new RightRegistration();
        Status statusid = new Status();
        Officer officerid = new Officer();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate accdate = LocalDate.parse(acceptdate, formatter);

        accepttouserid.setDateAccept(accdate);
        accepttouserid.setComment(comment);

        rightRegistrationid = rightRegistrationRepository.findByUsername(username);
        accepttouserid.setRightRegistration(rightRegistrationid);
        accepttouserid.setAccId(rightRegistrationid.getRegId());
        accepttouserid.setCodeAccept("F"+rightRegistrationid.getRegId());

        statusid = statusRepository.findByStatusName(statusname);
        accepttouserid.setStatus(statusid);

        officerid = officerRepository.findByOfficerName(officername);
        accepttouserid.setOfficer(officerid);

        return acceptToUserRepository.save(accepttouserid);
    }

    @PutMapping("/UpdateAccept/{id}/{acceptdate}/{codeAccept}/{username}/{comment}/{statusname}/{officername}")
    AcceptToUser putAccept(AcceptToUser accept, @PathVariable Long id,@PathVariable String acceptdate, @PathVariable String codeAccept
            , @PathVariable String comment ,@PathVariable String username,@PathVariable String statusname,@PathVariable String officername
    ) {
        return acceptToUserRepository.findById(id)
                .map(acc -> {
                            AcceptToUser accepttouserid = new AcceptToUser();
                            RightRegistration rightRegistrationid = new RightRegistration();
                            Status statusid = new Status();
                            Officer officerid = new Officer();

                            rightRegistrationid = rightRegistrationRepository.findByUsername(username);
                            statusid = statusRepository.findByStatusName(statusname);
                            officerid = officerRepository.findByUserName(officername);

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
                            LocalDate accdate = LocalDate.parse(acceptdate, formatter);
                            acc.setAccId(id);
                            acc.setDateAccept(accdate);
                            acc.setStatus(statusid);
                            acc.setCodeAccept(codeAccept);
                            acc.setComment(comment);
                            acc.setRightRegistration(rightRegistrationid);
                            acc.setOfficer(officerid);

                            return acceptToUserRepository.save(acc);
                        }
                ).orElseGet(() -> {
                    accept.setAccId(id);
                    return acceptToUserRepository.save(accept);
                });

    }
}
