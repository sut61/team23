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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.*;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="TypeOfPublicz") //ชื่อตาราง
public class TypeOfPublicz {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="typeofpublicz_seq",sequenceName="typeofpublicz_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="typeofpublicz_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="TYPEOFPUBLICZ_ID",unique = true, nullable = false)
    private Long typeOfPubliczID;

    //private @NonNull String typePubName;
    @NotNull(message = "typePubName must not be null")
    @Column(name="typePubName",unique = true)
    private  String typePubName;
//    ----------------------------

}