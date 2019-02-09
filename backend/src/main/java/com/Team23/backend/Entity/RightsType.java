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
@Table(name="RightsType") //ชื่อตาราง
public class RightsType {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="rightstype_seq",sequenceName="rightstype_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rightstype_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="RIGHTSTYPE_ID",unique = true, nullable = false)
    private @NonNull Long rightsTypeId;

    @NotNull(message="rightsTypeName must not be null to be valid")
    private String rightsTypeName;


}