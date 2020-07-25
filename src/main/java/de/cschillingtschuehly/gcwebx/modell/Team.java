package de.cschillingtschuehly.gcwebx.modell;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @OneToMany(targetEntity = Member.class)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List members;
}
