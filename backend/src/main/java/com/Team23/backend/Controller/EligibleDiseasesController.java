package com.Team23.backend.Controller;
import com.Team23.backend.Repository.*;
import com.Team23.backend.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EligibleDiseasesController {
    @Autowired private final EligibleDiseasesRepositoty EDR;
    @Autowired private DiseaseRepository DiseaseRepo;
    @Autowired private DocumentRepositoty DocRepo;
    @Autowired private OfficerRepository officerRepositoty;
    
    public EligibleDiseasesController(EligibleDiseasesRepositoty EDR){
        this.EDR = EDR;
    }

    @GetMapping("/EligibleDiseases")
    public Collection<EligibleDiseases> EligibleDiseases() {

        return EDR.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/checkuser/{n}.{p}")
    public Officer findUserName(Officer user,@PathVariable String n , @PathVariable String p) throws ChangeSetPersister.NotFoundException {



        try {
            user = officerRepositoty.findByUserName(n);
        }
        catch (Exception err) {
            throw new ChangeSetPersister.NotFoundException();
        }

        System.out.println(user.getPassWord().compareTo(p));
        if(user.getPassWord().compareTo(p) != 0){
            //System.out.println("Not Password!");
            //return false;
            throw new ChangeSetPersister.NotFoundException();
        }
        return user;
    }

    @PostMapping("/checkdoc/{numberDocument}")
    public DocumentWork findUserName(DocumentWork documentWork,@PathVariable String numberDocument) throws ChangeSetPersister.NotFoundException {
        try {
            documentWork = DocRepo.findBynumberDocument(numberDocument);
        }
        catch (Exception err) {
            throw new ChangeSetPersister.NotFoundException();
        }
        System.out.println(documentWork.getNumberDocument().compareTo(numberDocument));
        if(documentWork.getNumberDocument().compareTo(numberDocument) != 0){
            //System.out.println("Not Password!");
            //return false;
            throw new ChangeSetPersister.NotFoundException();
        }
        return documentWork;
    }

    @PostMapping("/EligibleDiseases/add/{Diseases},{NumberDocument},{username},{Code}")
    public  EligibleDiseases newEligibleDiseases(EligibleDiseases eligibleDiseases,@PathVariable String Diseases, @PathVariable String NumberDocument
    ,@PathVariable String username,@PathVariable String Code){

        Officer officers = officerRepositoty.findByUserName(username);
        Disease disease = DiseaseRepo.findByDiseaseName(Diseases);
        DocumentWork documentWork = DocRepo.findBynumberDocument(NumberDocument);



        eligibleDiseases.setDisease(disease);
        eligibleDiseases.setDocumentWork(documentWork);
        eligibleDiseases.setOfficer(officers);
        eligibleDiseases.setEligibleDiseasesCode(Code);
        return EDR.save(eligibleDiseases);
    }

    @RequestMapping(value ="/delete/{IDEligibleDiseases}")
    public  void  deleteStudyTimeTable(EligibleDiseases stt,@PathVariable Long IDEligibleDiseases){

        EDR.deleteById(IDEligibleDiseases);

    }
}
