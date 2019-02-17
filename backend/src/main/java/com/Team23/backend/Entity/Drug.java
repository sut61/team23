package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.*;
import java.time.format.DateTimeFormatter;
import java.time.*;

import java.util.*;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="Drug") //ชื่อตาราง
public class Drug {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="drug_seq",sequenceName="drug_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="drug_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="DRUG_ID",unique = true, nullable = false)
    private @NonNull Long drugId;
    
    // @Pattern(regexp="[a-zA-Z]*|[ก-์]*" ,message="drugName No have special character")
    @Size(min=2,max=30,message="drugName should not have alphabet at less 2 alphabet and than 30 alphabet")
    @NotNull(message="drugName must not be null to be valid")
    @Column(unique = true)
    private String drugName;


    @ManyToOne()
    @JoinColumn(name="typesOfDrugsnameId")
    @NotNull(message="typesOfDrugs must not be null to be valid")
    private TypesOfDrugs  typesOfDrugs;

    @ManyToOne()
    @JoinColumn(name=" drugRegistrationId")
    @NotNull(message="drugRegistration must not be null to be valid")
    private DrugRegistration  drugRegistration;

    @ManyToOne()
    @NotNull(message="typesOfDosageForms must not be null to be valid")
    @JoinColumn(name="typesOfDosageFormsNameId")
    private TypesOfDosageForms  typesOfDosageForms;

    @ManyToOne()
    @NotNull(message="disease must not be null to be valid")
    @JoinColumn(name="diseaseId")
    private Disease  disease;

    
    //เพิ่ม ID
    public Long findById(long drugId){
        return this.drugId=drugId;
    }
    public String deleteByDrugName(String drugName){
        return this.drugName=drugName;
    }

}