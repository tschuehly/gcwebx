package de.cschillingtschuehly.gcwebx.modell;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicUpdate
@SequenceGenerator(name = "seq",initialValue = 201)
@JsonView(value = View.Internal.class)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @JsonView(value = View.External.class)
    private Long memberId;
    @NotEmpty
    @JsonView(value = View.External.class)
    private String name;
    private String teamspeakId;
    private String generalInfo;
    private LocalDate dateOfBirth;
    private LocalDate joinDate;
    private LocalDate acceptanceDate;
    private String editor;
    private Integer warnings;
    @JsonView(value = View.External.class)
    private String gamerTag;
    @Column(columnDefinition = "boolean default false")
    private Boolean deleted;
    private String rank;
    @JsonView(value = View.External.class)
    private String twitter;
    @JsonView(value = View.External.class)
    private String twitch;
    @JsonView(value = View.External.class)
    private String youtube;
    private String cardImg;
    @JsonView(value = View.External.class)
    private String playerRole;

    public Member(String name, String teamspeakId, LocalDate dateOfBirth, LocalDate joinDate,String twitter,String youtube) {
        this.name = name;
        this.teamspeakId = teamspeakId;
        this.dateOfBirth = dateOfBirth;
        this.joinDate = joinDate;
        this.twitter = twitter;
        this.youtube = youtube;
    }

    public Member(@NotEmpty String name, String twitter, String youtube, String cardImg) {
        this.name = name;
        this.twitter = twitter;
        this.youtube = youtube;
        this.cardImg = cardImg;
        this.deleted = false;
    }
    /*
    public long getMemberId(){
        return memberId;
    }*/

}
