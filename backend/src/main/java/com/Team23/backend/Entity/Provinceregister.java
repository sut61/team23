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

@Entity // บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data // lombox จะสร้าง method getter setter ให้เอง
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "Province") // ชื่อตาราง
public class Provinceregister {
    @Id // Annotations @Id บอกว่าเป็น Primary key
    @SequenceGenerator(name = "Province_seq", sequenceName = "province_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_seq")
    // Annotations Generate id เอง ตอน insert
    @Column(name = "Province_ID", unique = true, nullable = false)
    private @NonNull Long provinceId;

    private @NonNull String provinceName;

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}