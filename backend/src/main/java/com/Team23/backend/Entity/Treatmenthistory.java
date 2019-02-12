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
import javax.validation.constraints.*;

import java.util.*;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="Treatmenthistory") //ชื่อตาราง
public class Treatmenthistory {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="treatmenthistory_seq",sequenceName="treatmenthistory_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="treatmenthistory_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="TREATMENTHISTORY_ID",unique = true, nullable = false)
    private @NonNull Long treatId;

    @NotNull(message="Code must not be null to be valid")
    @Pattern(regexp = "\\d{6,9}TH")
    @Size(min = 9, max = 11)
    @Column(name="code",unique = true)
    private String code;

    @NotNull(message="TreatDate must not be null to be valid")
    private LocalDate treatDate;

    @NotNull(message="Goldcards must not be null to be valid")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Goldcard.class)
    @JoinColumn(name= "goldcard", insertable = true)
    private Goldcard goldcards;

    @NotNull(message="Drugs must not be null to be valid")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Drug.class)
    @JoinColumn(name= "drug", insertable = true)
    private Drug drugs;

    @NotNull(message="Disease must not be null to be valid")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name= "disease", insertable = true)
    private Disease diseases;

}