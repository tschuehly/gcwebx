package de.cschillingtschuehly.gcwebx.modell;


import lombok.*;
import org.hibernate.engine.jdbc.spi.ConnectionObserverAdapter;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;

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
    private String title;
    @Lob
    @Column(columnDefinition = "text")
    private String text;
    private Boolean news;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdatedDate;



}
