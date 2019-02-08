package com.Team23.backend.Entity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;

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

@Table(name = "DocumentWork")
public class DocumentWork {
    @Id
    @SequenceGenerator(name="No_Doc",sequenceName="No_Doc")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="No_Doc")
    @Column(name = "No")
    private  @NotNull                              Long      IDDocument;
    private  @NotNull  @Pattern(regexp = "\\d+")   String    numberDocument;
    private  @NotNull  @Size(min = 5 , max = 40)   String    title;
    private  @NotNull  @Pattern(regexp = "http.+") String    url;
    private  @NotNull                              LocalDate date;

    public DocumentWork(String numberDocument,String title, String url){
        this.numberDocument = numberDocument;
        this.title          = title;
        this.url            = url;

    }
}
