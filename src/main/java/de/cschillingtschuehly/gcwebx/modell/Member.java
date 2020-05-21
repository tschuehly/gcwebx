package de.cschillingtschuehly.gcwebx.modell;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicUpdate
public class Member {
    @Id
    @GeneratedValue
    private Long memberId;
    @NotEmpty
    private String name;
    private String teamspeakId;
    private String generalInfo;
    private LocalDate dateOfBirth;
    private String desblTeam;
    private LocalDate joinDate;
    private LocalDate acceptanceDate;
    private String editor;
    private Integer warnings;
    private String uplayId;

    public Member(String name, String teamspeakId, LocalDate dateOfBirth, LocalDate joinDate) {
        this.name = name;
        this.teamspeakId = teamspeakId;
        this.dateOfBirth = dateOfBirth;
        this.joinDate = joinDate;
    }
}
