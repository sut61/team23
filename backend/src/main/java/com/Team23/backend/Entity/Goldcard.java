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
import javax.persistence.*;
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
@Table(name="Goldcard") //ชื่อตาราง
public class Goldcard {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="goldcard_seq",sequenceName="goldcard_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="goldcard_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="GOLDCARD_ID",unique = true, nullable = false)
    private @NonNull Long goldcardId;

    @Column(name = "goldcardName" ,unique = true)
    private @NonNull String goldcardName;

    @Pattern(regexp = "^\\S.+")
    private @NonNull String detail;

    //--------------------------------------Hospital
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hospital.class)
    @JoinColumn(name= "hospialName", insertable = true)
    private Hospital hospital;

    //--------------------------------------Officer
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
    @JoinColumn(name= "officerName", insertable = true)
    private Officer officer;

    //-----------------------------------------Member
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Member.class)
//    @JoinColumn(name= "memberName", insertable = true)
//    private Member member;
    //---------------------------------------RightRegistration
    @ManyToOne(fetch = FetchType.EAGER , targetEntity = RightRegistration.class)
    @JoinColumn(name = "username" ,insertable = true)
    private  RightRegistration rightRegistration;
}