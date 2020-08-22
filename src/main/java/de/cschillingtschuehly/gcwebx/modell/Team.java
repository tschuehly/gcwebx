package de.cschillingtschuehly.gcwebx.modell;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicUpdate
public class Team {
    @Id
    @GeneratedValue
    private Long teamId;
    @NotEmpty
    private String teamName;
    private String game;
    private String generalInfo;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Member.class ,orphanRemoval = true,fetch = FetchType.EAGER)
    private List members;

    public List<Member> addMember(Member pmember){
        this.members.add(pmember);
        return this.members;
    }
    public List<Member> removeMember(Member pmember){
        this.members.remove(pmember);
        return this.members;
    }

}
