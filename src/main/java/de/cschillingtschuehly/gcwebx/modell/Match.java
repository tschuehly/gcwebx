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
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    @OneToOne
    private Team hometeam;
    private String title;
    private String opponent;
    private String opponentLogo;
    private Integer scoreHome;
    private Integer scoreOpponent;

}
