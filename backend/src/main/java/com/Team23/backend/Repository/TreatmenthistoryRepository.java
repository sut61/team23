package com.Team23.backend.Repository;

import com.Team23.backend.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
public interface TreatmenthistoryRepository extends  JpaRepository<Treatmenthistory ,Long> {

}
