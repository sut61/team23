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
@Table(name="AcceptToUser") //ชื่อตาราง
public class AcceptToUser {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @Column(name="ACC_ID",unique = true, nullable = false)
    private Long accId;

    @Column(name="codeAccept",unique = true)
    @NotNull(message="codeAccept must not be null to be valid")
    @Pattern(regexp = "[PF]\\d*")
    private  String codeAccept;
    @Size(min=3,max=150)
    private  String comment;

    private  LocalDate dateAccept;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RightRegistration.class)
    @JoinColumn(name= "RIGHTREGISTRATION_ID", insertable = true)
    private RightRegistration rightRegistration;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
    @JoinColumn(name= "STATUS_ID", insertable = true)
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
    @JoinColumn(name= "OFFICER_ID", insertable = true)
    private Officer officer;

}
