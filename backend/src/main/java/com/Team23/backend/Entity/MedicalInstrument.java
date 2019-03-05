package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Data

public class MedicalInstrument{
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="medicalinsturment_seq",sequenceName="medicalinsturment_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="medicalinsturment_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="MEDICALINSTRUMENT_ID",unique = true, nullable = false)
    private Long medicalInstrumentId;


    @Pattern(regexp="[a-zA-Z]*|[ก-์]*" ,message="medicalInstrumentName No have special character")
    @Size(min=2,max=100,message="medicalInstrumentName should not have alphabet at less 2 alphabet and than 30 alphabet")
    @Column(unique = true)
    @NotNull(message="medicalInstrumentName must not be null to be valid")
    private String  medicalInstrumentName;

    public MedicalInstrument(){}
}