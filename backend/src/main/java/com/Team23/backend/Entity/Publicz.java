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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.*;

import java.util.*;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="Publicz") //ชื่อตาราง
public class Publicz {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="publicz_seq",sequenceName="publicz_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="publicz_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="PUBLICZ_ID",unique = true, nullable = false)
    private @NotNull Long publiczID;

    //private @NonNull String publiczHead;
    @NotNull(message = "Header must not be null")
    @Column(name="publiczHead",unique = true)
    @Size(min = 7 ,max = 20)
    private  String publiczHead;
//    ---------------------------------------------------------
    @Pattern(regexp = "^\\S.+")
    private @NonNull String PublicizeDetail;
//--------------------------------------------------------------------ช่องทางติดต่อ
    @Pattern(regexp = "\\w{2,10}[@]\\w{2,10}.com")
    private  @NonNull String email;
    @Pattern(regexp = "[0][896]\\d{8}")
//    @NotNull(message = "PhoneNumeber must not be null") //---------------------------ใส่แล้วแดง
    private String call;
//    ------------------------------------------------------
    private @NonNull LocalDate date_reg;

    //--------------------------------------Hospital
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hospital.class)
    @JoinColumn(name= "hospialName", insertable = true)
    private Hospital hospital;

    //--------------------------------------Officer
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
    @JoinColumn(name= "officerName", insertable = true)
    private Officer officer;

    //-----------------------------------------TypeOfPublicz
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeOfPublicz.class)
    @JoinColumn(name= "typePubName", insertable = true)
    private TypeOfPublicz TypeOfPublicz;
}