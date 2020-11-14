package de.cschillingtschuehly.gcwebx.modell;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@JsonView(View.External.class)
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
