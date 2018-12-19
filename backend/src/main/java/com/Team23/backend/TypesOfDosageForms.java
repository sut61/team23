package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@ToString @EqualsAndHashCode
public class TypesOfDosageForms {
        @Id @GeneratedValue
        private Long typesOfDosageFormsId;
        private @NonNull String typesOfDosageFormsName;
        
        public TypesOfDosageForms(){}

        public TypesOfDosageForms(String typesOfDosageFormsName){
                this.typesOfDosageFormsName=typesOfDosageFormsName;
        }
        public TypesOfDosageForms(Long typesOfDosageFormsId){
                this.typesOfDosageFormsId=typesOfDosageFormsId;
        }

}