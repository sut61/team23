package com.Team23.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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