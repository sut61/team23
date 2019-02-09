package com.Team23.backend.Repository;

import com.Team23.backend.Entity.MedicalInstrument;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface MedicalInstrumentRepository extends JpaRepository<MedicalInstrument,Long> {
    MedicalInstrument findById(long medicalInstrumentId);
    MedicalInstrument findByMedicalInstrumentName(String  medicalInstrumentName);
}