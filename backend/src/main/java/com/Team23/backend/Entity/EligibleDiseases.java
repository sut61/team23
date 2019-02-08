package com.Team23.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Column( unique = true)
    private @NotNull @Pattern(regexp = "El.+") @Size(min = 5 , max = 25) String EligibleDiseasesCode;
    @ManyToOne Disease disease;
    @ManyToOne Officer officer;
    @ManyToOne DocumentWork documentWork;
    public EligibleDiseases( Disease diseaser,DocumentWork documentWork, Officer officer,String EligibleDiseasesCode){
        this.documentWork           = documentWork;
        this.disease                = diseaser;
        this.EligibleDiseasesCode   = EligibleDiseasesCode;
        this.officer                = officer;
    }
}
