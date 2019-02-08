package com.Team23.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Card")
public class Card {
    @Id
    @SequenceGenerator(name="No_Card",sequenceName="No_Card")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="No_Card")
    @Column(name = "No")
    private  @NotNull Long Idcard;
    private  @NotNull String cardcord;
    private  @NotNull LocalDate date;
    private  @NotNull @ManyToOne
    AcceptToUser acceptToUser;
    private  @NotNull @ManyToOne Expenses expenses;
    private  @NonNull String comment;
    public Card(String cardcord, LocalDate date, AcceptToUser acceptToUser, Expenses expenses, String comment){
        this.acceptToUser = acceptToUser;
        this.cardcord = cardcord;
        this.date = date;
        this.expenses = expenses;
        this.comment = comment;
    }
}