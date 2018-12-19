package com.Team23.backend.entity;
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

import java.util.Collection;

@Entity
@Data
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="Blood") //ชื่อตาราง
public class Blood {
    @Id
    @SequenceGenerator(name = "Blood_seq", sequenceName = "Blood_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Blood_seq")
    @Column(name = "Blood_ID", unique = true, nullable = false)

    private @NonNull Long BloodTypeId;
    private @NonNull String BType;





}