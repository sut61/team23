package com.Team23.backend.Repository;
import com.Team23.backend.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface ExpensesRepository extends  JpaRepository<Expenses ,Long>{
    Expenses findByIdExpenses(Long IdExpenses);

}
