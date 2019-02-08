package com.Team23.backend.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Expenses")
public class Expenses {
    @Id
    @SequenceGenerator(name="No_Expenses",sequenceName="No_Expenses")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="No_Expenses")
    @Column(name = "No")
    private  @NotNull Long idExpenses;
    private @NotNull String ExpensesName;
    private @NotNull int Number;

    public Expenses(String Expensesname, int Number){
        this.ExpensesName = Expensesname;
        this.Number = Number;
    }
}
