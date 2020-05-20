package de.cschilingtschuehly.gcwebx.modell;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String teamspeakId;
    private String generalInfo;
    private Date dateOfBirth;
    private String desblTeam;
    private Date joinDate;
    private Date acceptanceDate;
    private String editor;
    private Integer warnings;
}
