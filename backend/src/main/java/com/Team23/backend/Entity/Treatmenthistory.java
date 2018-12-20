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
    private @NonNull LocalDate treatDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Goldcard.class)
    @JoinColumn(name= "goldcard", insertable = true)
    private Goldcard goldcards;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Drug.class)
    @JoinColumn(name= "drug", insertable = true)
    private Drug drugs;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name= "disease", insertable = true)
    private Disease diseases;

}