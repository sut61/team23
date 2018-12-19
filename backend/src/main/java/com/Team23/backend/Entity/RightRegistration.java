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
@Table(name="RightRegistration") //ชื่อตาราง
public class RightRegistration {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="reg_seq",sequenceName="reg_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reg_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="REG_ID",unique = true, nullable = false)
    private @NonNull Long regId;

    @Column(name="username",unique = true)
    private @NonNull String username;
    private @NonNull String password;
    private @NonNull String firstname;
    private @NonNull String surname;
    private @NonNull String tel;
    private @NonNull Long personalcard;
    private @NonNull LocalDate dateregis;
    private @NonNull LocalDate birthday;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name= "PROVINCE_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RightsType.class)
    @JoinColumn(name= "RIGHTSTYPE_ID", insertable = true)
    private RightsType rightsType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = gHostpital.class)
    @JoinColumn(name= "GHOSTPITAL_ID", insertable = true)
    private gHostpital ghostpital;

}