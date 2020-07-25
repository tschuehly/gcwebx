package de.cschillingtschuehly.gcwebx.modell;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDate;
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
    private LocalDate joinDate;
    private LocalDate acceptanceDate;
    private String editor;
    private Integer warnings;
    private String uplayId;
    @Column(columnDefinition = "boolean default false")
    private Boolean deleted;
    private String rank;
    private URL twitter;
    private URL twitch;
    private URL youtube;
    private String cardImg;

    public Member(String name, String teamspeakId, LocalDate dateOfBirth, LocalDate joinDate,URL twitter,URL youtube) {
        this.name = name;
        this.teamspeakId = teamspeakId;
        this.dateOfBirth = dateOfBirth;
        this.joinDate = joinDate;
        this.twitter = twitter;
        this.youtube = youtube;
    }
    public Member(Long memberId,String name, String teamspeakId, LocalDate dateOfBirth, LocalDate joinDate,URL twitter,URL youtube,String cardImg) {
        this.memberId = memberId;
        this.name = name;
        this.teamspeakId = teamspeakId;
        this.dateOfBirth = dateOfBirth;
        this.joinDate = joinDate;
        this.twitter = twitter;
        this.youtube = youtube;
        this.cardImg = cardImg;
    }

    public long getMemberId(){
        return memberId;
    }

}
