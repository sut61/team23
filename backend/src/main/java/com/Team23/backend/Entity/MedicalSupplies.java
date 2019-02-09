package com.Team23.backend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.pl.PESEL;

import lombok.Data;

@Entity
@Data
public class MedicalSupplies {
    @Id  
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    private Long medicalsuppliesId;

    @Column(unique = true)
    private String codeNumber;

    @Column(unique = true)
    private String modelNumber;

    @Pattern(regexp="[a-zA-Z]*|[ก-์]" ,message="medicalsuppliesName No have special character")
    @Size(min=2,max=30,message="medicalsuppliesName should not have alphabet at less 2 alphabet and than 30 alphabet")
    @NotNull(message="medicalsuppliesName must not be null to be valid")
    @Column(unique = true)
    private String medicalsuppliesName;

    @Size(min=2,max=30,message="medicalsuppliesName should not have alphabet at less 2 alphabet and than 30 alphabet")
    private String brandName;

    @Size(min=2,max=30,message="medicalsuppliesName should not have alphabet at less 2 alphabet and than 30 alphabet")
    private String properties;


    @ManyToOne()    
    @JoinColumn(name="medicalInstrumentId")
    private MedicalInstrument medicalInstrument;

    @ManyToOne()
    @JoinColumn(name="useabilityId")
    private Useability useability;

    public MedicalSupplies(){}
    public Long findById(long medicalsuppliesId){
        return this.medicalsuppliesId=medicalsuppliesId;
    }
    public String deleteByMedicalsuppliesName(String medicalsuppliesName){
        return this.medicalsuppliesName=medicalsuppliesName;
    }
}