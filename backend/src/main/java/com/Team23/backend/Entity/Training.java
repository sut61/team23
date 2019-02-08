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
@Table(name = "Training") // ชื่อตาราง
public class Training {
    @Id // Annotations @Id บอกว่าเป็น Primary key
    @SequenceGenerator(name = "Training_seq", sequenceName = "training_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_seq")
    // Annotations Generate id เอง ตอน insert
    @Column(name = "Training_ID", unique = true, nullable = false)
    private @NonNull Long trainingId;

    private @NonNull String topicTraining;
    private @NonNull String objectiveTraining;
    private @NonNull String importantTopicTraining;
    private @NonNull Long attendess;
    private @NonNull Long expenditure;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Lecturer.class)
    @JoinColumn(name = "training_lecturer", insertable = true)
    private Lecturer lecturerName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeTraining.class)
    @JoinColumn(name = "training_type", insertable = true)
    private TypeTraining typeTrainingName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hospital.class)
    @JoinColumn(name = "Training_Hospital",insertable= true)
    private Hospital hospitalName;

    /**
     * @param importantTopicTraining the importantTopicTraining to set
     */
    public void setImportantTopicTraining(String importantTopicTraining) {
        this.importantTopicTraining = importantTopicTraining;
    }
    /**
     * @param lecturerName the lecturerName to set
     */
    public void setLecturerName(Lecturer lecturerName) {
        this.lecturerName = lecturerName;
    }
    /**
     * @param objectiveTraining the objectiveTraining to set
     */
    public void setObjectiveTraining(String objectiveTraining) {
        this.objectiveTraining = objectiveTraining;
    }
    /**
     * @param hospitalName the hospitalName to set
     */
    public void setHospitalName(Hospital hospitalName) {
        this.hospitalName = hospitalName;
    }
    /**
     * @param topicTraining the topicTraining to set
     */
    public void setTopicTraining(String topicTraining) {
        this.topicTraining = topicTraining;
    }

    /**
     * @param trainingId the trainingId to set
     */
    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }
    /**
     * @param typeTrainingName the typeTrainingName to set
     */
    public void setTypeTrainingName(TypeTraining typeTrainingName) {
        this.typeTrainingName = typeTrainingName;
    }

    /**
     * @param attendess the attendess to set
     */
    public void setAttendess(Long attendess) {
        this.attendess = attendess;
    }
    /**
     * @param expenditure the expenditure to set
     */
    public void setExpenditure(Long expenditure) {
        this.expenditure = expenditure;
    }

    
}