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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;

@Entity // บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data // lombox จะสร้าง method getter setter ให้เอง
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "Lecturer") // ชื่อตาราง
public class Lecturer {
    @Id // Annotations @Id บอกว่าเป็น Primary key
    @SequenceGenerator(name = "Lecturer_seq", sequenceName = "lecturer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecturer_seq")
    // Annotations Generate id เอง ตอน insert
    @Column(name = "Lecturer_ID", unique = true, nullable = false)
    private Long lecturerId;


    @NotNull
    private String lecturerName;
    

    /**
     * @param lecturerId the lecturerId to set
     */
    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }
    /**
     * @param lecturerName the lecturerName to set
     */
    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }
    
}