package com.Team23.backend.Entity;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "EligibleDiseases")
public class EligibleDiseasesEntity {
    @Id
    @SequenceGenerator(name="No_STT",sequenceName="No_STT")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="No_STT")
    @Column(name = "No")
    private  @NonNull  Long IDEligibleDiseases;
}
