package com.Team23.backend.Repository;


import com.Team23.backend.Entity.TypesOfDosageForms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface TypesOfDosageFormsRepository extends JpaRepository<TypesOfDosageForms,Long>{
    TypesOfDosageForms findById(long typesOfDosageFormsId);
    TypesOfDosageForms findByTypesOfDosageFormsName(String typesOfDosageFormsName);

}