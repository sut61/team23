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
@Table(name = "Expenses")
public class Expenses {
    @Id
    @SequenceGenerator(name="No_Expenses",sequenceName="No_Expenses")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="No_Expenses")
    @Column(name = "No")
    private  @NotNull                           Long        idExpenses;
    private  @NotNull                           String      ExpensesName;
    private  @NotNull @Pattern(regexp = "\\d+") String      Number;
    private  @NotNull @Size(min = 6 , max = 40) String      comment;
    private  @NotNull                           LocalDate   date;
    public Expenses(String Expensesname, String Number,String comment){
        this.ExpensesName    = Expensesname;
        this.Number          = Number;
        this.comment         = comment;
    }
}
