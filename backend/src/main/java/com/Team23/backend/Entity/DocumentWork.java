package com.Team23.backend.Entity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

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
    private  @NonNull  Long IDDocument;
    private  @NonNull String numberDocument;
    private  @NonNull String title;
    private  @NonNull String url;

    public DocumentWork(String numberDocument,String title, String url){
        this.numberDocument= numberDocument;
        this.title=title;
        this.url=url;
    }
}
