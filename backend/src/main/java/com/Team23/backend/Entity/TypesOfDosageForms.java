package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@ToString @EqualsAndHashCode
public class TypesOfDosageForms {
        @Id 
        @GeneratedValue(strategy= GenerationType.IDENTITY) //this isolate id 1 2 3
        private @NonNull Long typesOfDosageFormsId;
        @NotNull(message="typesOfDosageFormsName must not be null to be valid")
        private String typesOfDosageFormsName;
public TypesOfDosageForms(){}

}