package de.cschillingtschuehly.gcwebx.modell;

import com.fasterxml.jackson.annotation.JsonView;
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
@JsonView(View.External.class)
public class Team {
    @Id
    @GeneratedValue
    private Long teamId;
    @NotEmpty
    private String teamName;
    private String game;
    private String generalInfo;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Member.class,fetch = FetchType.EAGER)
    private List<Member> members;

    public List<Member> addMember(Member pmember){
        this.members.add(pmember);
        return this.members;
    }
    public List<Member> removeMember(Member pmember){
        members.stream().filter(member -> member.getMemberId().equals(pmember.getMemberId())).findFirst().ifPresent(member -> members.remove(member));
        System.out.println(this.members);
        return this.members;
    }

}
