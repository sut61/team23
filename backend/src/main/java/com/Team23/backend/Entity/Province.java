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
@Table(name="Province") //ชื่อตาราง
public class Province {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="province_seq",sequenceName="province_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="province_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="PROVINCE_ID",unique = true, nullable = false)
    @NotNull(message="provinceId must not be null to be valid")
    private Long provinceId;

    @NotNull(message="provinceName must not be null to be valid")
    private String provinceName;

}