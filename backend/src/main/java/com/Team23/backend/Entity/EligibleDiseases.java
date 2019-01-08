package com.Team23.backend.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "EligibleDiseases")
public class EligibleDiseases {
    @Id
    @SequenceGenerator(name="No_ED",sequenceName="No_ED")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="No_ED")
    @Column(name = "No")
    private  @NonNull  Long IDEligibleDiseases;
    @ManyToOne Disease disease;
    @ManyToOne Officer officer;
    @ManyToOne DocumentWork documentWork;
    public EligibleDiseases( Disease diseaser,DocumentWork documentWork, Officer officer){
this.documentWork = documentWork;
        this.disease = diseaser;

     this.officer = officer;
    }
}
