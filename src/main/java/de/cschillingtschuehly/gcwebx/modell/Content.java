package de.cschillingtschuehly.gcwebx.modell;


import lombok.*;
import org.hibernate.engine.jdbc.spi.ConnectionObserverAdapter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Content{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column(columnDefinition = "text")
    String text;
    private LocalDate creationDate;
    private LocalDate lastUpdatedDate;



}
