package com.Team23.backend.Entity;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.*;

@Entity
@Data

public class MedicalInstrument{
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="medicalinsturment_seq",sequenceName="medicalinsturment_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="medicalinsturment_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="MEDICALINSTRUMENT_ID",unique = true, nullable = false)
    private Long medicalInstrumentId;
    private String  medicalInstrumentName;

    public MedicalInstrument(){}
}