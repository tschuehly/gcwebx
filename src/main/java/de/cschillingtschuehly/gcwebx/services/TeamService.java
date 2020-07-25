package de.cschillingtschuehly.gcwebx.services;

import de.cschillingtschuehly.gcwebx.modell.Team;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import de.cschillingtschuehly.gcwebx.repositories.TeamRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public List<Team> getTeams(){
        return teamRepository.findAll();
    }
}
