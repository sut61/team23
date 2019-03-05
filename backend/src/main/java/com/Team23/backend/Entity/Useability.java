package com.Team23.backend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.*;

@Entity
@Data
@ToString @EqualsAndHashCode
public class Useability {
    @Id  //  Annotations  @Id  บอกว่าเป็น  Primary  key
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    private @NonNull Long useabilityId;


    @Pattern(regexp="[a-zA-Z]*|[ก-์]*" ,message="useabilityName No have special character")
    @Size(min=2,max=100,message="useabilityName should not have alphabet at less 2 alphabet and than 30 alphabet")
    @Column(unique = true)
    @NotNull(message="useabilityName must not be null to be valid")
    private String useabilityName;
    public Useability(){}
}