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
@Table(name="Drug") //ชื่อตาราง
public class Drug {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="drug_seq",sequenceName="drug_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="drug_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="DRUG_ID",unique = true, nullable = false)
    private @NonNull Long drugId;
    private @NonNull String drugName;


}