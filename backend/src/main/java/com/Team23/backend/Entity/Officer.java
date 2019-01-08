package com.Team23.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    private @NonNull Long idOfficer;
    private @NonNull String Name;
    @JsonIgnore
    private @NonNull String userName;
    @JsonIgnore
    private @NonNull String passWord;
    public Officer(String Name, String userName, String passWord){
        this.Name = Name;
        this.userName = userName;
        this.passWord = passWord;
    }
}
