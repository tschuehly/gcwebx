package de.cschillingtschuehly.gcwebx.modell;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    String text;

}
