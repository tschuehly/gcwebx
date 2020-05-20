package de.cschillingtschuehly.gcwebx.modell;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import javax.persistence.Entity;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String teamspeakId;
    private String generalInfo;
    private LocalDate dateOfBirth;
    private String desblTeam;
    private LocalDate joinDate;
    private LocalDate acceptanceDate;
    private String editor;
    private Integer warnings;

    public Member(String name, String teamspeakId, LocalDate dateOfBirth, LocalDate joinDate) {
        this.name = name;
        this.teamspeakId = teamspeakId;
        this.dateOfBirth = dateOfBirth;
        this.joinDate = joinDate;
    }
}
