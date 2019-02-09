package com.Team23.backend.Repository;

import com.Team23.backend.Entity.MedicalSupplies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface MedicalSuppliesRepository extends JpaRepository<MedicalSupplies,Long>{
    MedicalSupplies findById(long medicalsuppliesId);
    MedicalSupplies findBymedicalsuppliesName(String medicalsuppliesName);
    void deleteByMedicalsuppliesName(String medicalsuppliesName);
}