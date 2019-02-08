package com.Team23.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    private  @NotNull                               Long         Idcard;
    private  @NotNull @Pattern(regexp = "card.+")   String       cardcord;
    private  @NotNull                               LocalDate    date;
    private  @NotNull @Size(min = 5 , max = 25)     String       comment;
    private  @NonNull @ManyToOne                    AcceptToUser acceptToUser;
    private  @NotNull @ManyToOne                    Expenses     expenses;

    public Card(String cardcord, AcceptToUser acceptToUser, Expenses expenses, String comment){
        this.acceptToUser    = acceptToUser;
        this.cardcord        = cardcord;
        this.expenses        = expenses;
        this.comment         = comment;
    }
}