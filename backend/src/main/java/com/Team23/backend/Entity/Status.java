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
@Table(name="Status") //ชื่อตาราง
public class Status {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @SequenceGenerator(name="status_seq",sequenceName="status_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="status_seq")   // Annotations Generate id เอง ตอน insert
    @Column(name="STATUS_ID",unique = true, nullable = false)
    private @NonNull Long statusId;
    @NotNull(message="statusName must not be null to be valid")
    private String statusName;

}