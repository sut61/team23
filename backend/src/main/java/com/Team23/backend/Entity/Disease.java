package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.*;
import java.time.format.DateTimeFormatter;
import java.time.*;

import java.util.*;
import javax.validation.constraints.*;


@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="Disease") //ชื่อตาราง
public class Disease {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="disease_seq",sequenceName="disease_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="disease_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="DISEASE_ID",unique = true, nullable = false)
    private @NonNull Long diseaseId;

    @Pattern(regexp = "(โรค).+")
    @Size(min = 5, max = 30)
    @Column(name="diseaseName",unique = true)
    @NotNull(message="DiseaseName must not be null to be valid")
    private String diseaseName;

    @Size(min = 5, max = 40)
    @NotNull(message="Symptom must not be null to be valid")
    private String symptom;

    @NotNull(message="Cause must not be null to be valid")
    private String cause;

    @NotNull(message="Remedy must not be null to be valid")
    private String remedy;

    @NotNull(message="TypeDisease must not be null to be valid")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeDisease.class)
    @JoinColumn(name= "typedisease", insertable = true)
    private TypeDisease typedisease;

    @NotNull(message="PeopleDisease must not be null to be valid")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PeopleDisease.class)
    @JoinColumn(name= "peopledisease", insertable = true)
    private PeopleDisease peopledisease;
}