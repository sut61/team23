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
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@ToString @EqualsAndHashCode
public class DrugRegistration {
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY) //this isolate id 1 2 3
    private @NonNull Long drugRegistrationId;


    @Pattern(regexp="[a-zA-Z]*|[ก-์]*" ,message="drugRegistrationName No have special character")
    @Size(min=2,max=100,message="drugRegistrationName should not have alphabet at less 2 alphabet and than 30 alphabet")
    @Column(unique = true)
    @NotNull(message="drugRegistrationName must not be null to be valid")
    private String drugRegistrationName;

 public DrugRegistration(){}
 
}
