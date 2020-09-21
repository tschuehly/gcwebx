package de.cschillingtschuehly.gcwebx.modell;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    @OneToOne
    private Team hometeam;
    private String title;
    private String opponent;
    private String opponentLogo;
    private Integer scoreHome;
    private Integer scoreOpponent;

}
