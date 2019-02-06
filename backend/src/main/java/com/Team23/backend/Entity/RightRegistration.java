package com.Team23.backend.Entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.*;
import java.util.*;
import lombok.*;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Data
@Entity
@Table(name="RightRegistration") //ชื่อตาราง
public class RightRegistration {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="reg_seq",sequenceName="reg_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reg_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="REG_ID",unique = true, nullable = false)
    private Long regId;

    @NotNull(message="username must not be null to be valid")
    @Column(name="username",unique = true)
    @Size(min=3,max=20)
    private  String username;
    @NotNull(message="password must not be null to be valid")
    private  String password;
    @NotNull(message="firstname must not be null to be valid")
    private  String firstname;
    @NotNull(message="surname must not be null to be valid")
    private  String surname;
    @NotNull(message="tel must not be null to be valid")
    @Pattern(regexp = "[0]\\d{9}")
    private  String tel;
    @NotNull(message="personalcard must not be null to be valid")
    @Column(name="personalcard",unique = true)
    private  Long personalcard;
    private  LocalDate dateregis;
    private  LocalDate birthday;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name= "PROVINCE_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RightsType.class)
    @JoinColumn(name= "RIGHTSTYPE_ID", insertable = true)
    private RightsType rightsType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hospital.class)
    @JoinColumn(name= "HOSPITAL_ID", insertable = true)
    private Hospital hospital;

}
