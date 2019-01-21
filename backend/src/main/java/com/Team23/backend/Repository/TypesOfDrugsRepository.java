package com.Team23.backend.Repository;

import com.Team23.backend.Entity.TypesOfDrugs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface TypesOfDrugsRepository extends JpaRepository<TypesOfDrugs,Long>{
    TypesOfDrugs findById(long typesOfDrugsId);
    TypesOfDrugs findByTypesOfDrugsName(String typesOfDrugsName);
}