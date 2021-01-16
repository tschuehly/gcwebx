package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.cschillingtschuehly.gcwebx.helpers.CRUDService;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.Team;
import de.cschillingtschuehly.gcwebx.repositories.MatchRepository;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import de.cschillingtschuehly.gcwebx.repositories.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeamService extends CRUDService<Team, TeamRepository> {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private TeamRepository teamRepository;

    private final Logger logger = LoggerFactory.getLogger(TeamService.class);

    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    public Team updateTeam(Team teamParam) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        Team team = teamRepository.findById(teamParam.getTeamId())
                .orElseThrow(EntityNotFoundException::new);
        jacksonMapper.readerForUpdating(team).readValue(jacksonMapper.writeValueAsString(teamParam));
        teamRepository.save(team);
        return team;
    }

    public String createTeam(Team teamParam) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        teamRepository.save(teamParam);
        Team team = teamRepository.findById(teamParam.getTeamId()).get();
        return jacksonMapper.writeValueAsString(team);
    }

    public void deleteTeam(Team teamParam){
        matchRepository.deleteByHometeam(teamParam);
        teamRepository.delete(teamParam);

    }

    public Team addMember(Long teamId,Member pmember) {
        Team team = teamRepository.findById(teamId).get();
        team.addMember(memberRepository.findById(pmember.getMemberId()).get());
        try{
            teamRepository.save(team);
        }catch (Exception e){
            // TODO: return error
            logger.info("Could not add Member");
        }
        return team;
    }

    public Team removeMemberFromTeam(Long teamId,Member pmember) {
        Team team = teamRepository.findById(teamId).get();
        team.removeMember(memberRepository.findById(pmember.getMemberId()).get());
        return teamRepository.save(team);
    }


    public void removeMember(Member pmember){
        Team team = teamRepository.findByMembersMemberId(pmember.getMemberId());
        team.removeMember(pmember);
        teamRepository.save(team);
    }
}
