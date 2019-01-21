package com.Team23.backend.Repository;

import com.Team23.backend.Entity.DrugRegistration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface DrugRegistrationRepository extends JpaRepository<DrugRegistration,Long>{
    DrugRegistration findById(long drugRegistrationId);
    DrugRegistration findByDrugRegistrationName(String drugRegistrationName);
}