package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "Hospital") // ชื่อตาราง
public class Hospital {
    @Id // Annotations @Id บอกว่าเป็น Primary key
    @SequenceGenerator(name = "Hospital_seq", sequenceName = "hospital_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq")
    // Annotations Generate id เอง ตอน insert
    @Column(name = "Hospital_ID", unique = true, nullable = false)
    private  Long hospitalId;
    @NotNull
    @Size(min=3,max=72)
    private  String hospitalName;

    @NotNull
    @Column(name="branceNine",unique = true)
    private  Long branceNine;
    @NotNull
    private  Long branceFive;
    @NotNull
    private  String hospitalAddress;
    @NotNull
    private  Long hospitalPostcode;

    @NotNull
    @Pattern(regexp = "[0]\\d{9}")
    private  String hospitalPhone;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Affiliation.class)
    @JoinColumn(name = "hospital_affiliation", insertable = true)
    private Affiliation affiliationName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "hospital_province", insertable = true)
    private Province provinceName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeHospital.class)
    @JoinColumn(name = "hospital_type", insertable = true)
    private TypeHospital typeName;

    public void setBranceFive(Long branceFive) {
        this.branceFive = branceFive;
    }

    public void setBranceNine(Long branceNine) {
        this.branceNine = branceNine;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public void setHospitalPostcode(Long hospitalPostcode) {
        this.hospitalPostcode = hospitalPostcode;
    }

    public void setTypeName(TypeHospital typeName) {
        this.typeName = typeName;
    }

    public void setAffiliationName(Affiliation affiliationName) {
        this.affiliationName = affiliationName;
    }
    /**
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(Province provinceName) {
        this.provinceName = provinceName;
    }
    
}