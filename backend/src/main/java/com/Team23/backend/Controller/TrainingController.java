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
public class TrainingController {
    @Autowired
    private final TrainingRepository trainingRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private TypeTrainingRepository typeTrainingRepository;

    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @GetMapping("/listtraining")
    public Collection<Training> training() {
        return trainingRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/addtraining/{topicTraining}/{objectiveTraining}/{importantTopicTraining}/{lecturerName}/{typeTrainingName}/{hospitalName}/{attendess}/{expenditure}")
    public Training newTraining(@PathVariable String topicTraining, 
            @PathVariable String objectiveTraining,
            @PathVariable String importantTopicTraining, 
            @PathVariable String lecturerName, 
            @PathVariable String typeTrainingName,
            @PathVariable String hospitalName,
            @PathVariable Long attendess,
            @PathVariable Long expenditure ) {
        Training train = new Training();
        train.setTopicTraining(topicTraining);
        train.setObjectiveTraining(objectiveTraining);
        train.setImportantTopicTraining(importantTopicTraining);
        train.setAttendess(attendess);
        train.setExpenditure(expenditure);

         
        Lecturer lec = lecturerRepository.findByLecturerName(lecturerName);
        train.setLecturerName(lec);

        TypeTraining typ = typeTrainingRepository.findByTypeTrainingName(typeTrainingName);
        train.setTypeTrainingName(typ);

        Hospital hos = hospitalRepository.findByHospitalName(hospitalName);
        train.setHospitalName(hos);

        return trainingRepository.save(train);


    }

}