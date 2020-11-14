package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.Team;
import de.cschillingtschuehly.gcwebx.repositories.MatchRepository;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import de.cschillingtschuehly.gcwebx.repositories.TeamRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private
    TeamRepository teamRepository;

    public List<Team> getTeams(){
        return teamRepository.findAll();
    }

    public String updateTeam(Team teamParam) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        Mapper dozerMapper = mapperService.dozerMapper();
        Team team = teamRepository.findById(teamParam.getTeamId()).get();
        dozerMapper.map(teamParam,team);
        teamRepository.save(team);
        return jacksonMapper.writeValueAsString(team);
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

    public String addMember(Long teamId,Member pmember) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        Member member = memberRepository.findById(pmember.getMemberId()).get();
        Team team = teamRepository.findById(teamId).get();
        team.addMember(member);
        teamRepository.save(team);
        return jacksonMapper.writeValueAsString(team);
    }

    public String removeMemberFromTeam(Long teamId,Member pmember) throws JsonProcessingException {
        System.out.println(teamId);
        System.out.println(pmember);
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        Member member = memberRepository.findById(pmember.getMemberId()).get();
        Team team = teamRepository.findById(teamId).get();
        team.removeMember(member);
        teamRepository.save(team);
        return jacksonMapper.writeValueAsString(team);
    }


    public void removeMember(Member pmember){
        Team team = teamRepository.findByMembersMemberId(pmember.getMemberId());
        System.out.println(team);
        System.out.println(team.removeMember(pmember));
        System.out.println(team);
        teamRepository.save(team);
    }
}
