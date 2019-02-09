package com.Team23.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String useabilityName;
    public Useability(){}
}