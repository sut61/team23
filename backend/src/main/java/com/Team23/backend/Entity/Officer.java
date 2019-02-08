package com.Team23.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Officer")
public class Officer {
    @Id
        @SequenceGenerator(name="No_Off",sequenceName="No_Off")
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="No_Off")
        @Column(name = "No")
        private @NotNull                                Long   idOfficer;
        private @NotNull                                String officerName;
        private @NotNull    @Size(min = 5 , max = 40)   String userName;
        private @NotNull    @Size(min = 4 , max = 20)   String passWord;
        private @NotNull    @Pattern(regexp = "\\d+")   String callNumber;
        public Officer(String Name, String userName, String passWord ,String callNumber){
            this.officerName     = Name;
            this.userName        = userName;
            this.passWord        = passWord;
            this.callNumber      = callNumber;
        }
}
