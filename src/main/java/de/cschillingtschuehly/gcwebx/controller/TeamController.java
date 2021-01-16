package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.helpers.RESTController;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.Team;
import de.cschillingtschuehly.gcwebx.modell.View;
import de.cschillingtschuehly.gcwebx.repositories.TeamRepository;
import de.cschillingtschuehly.gcwebx.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/team")
public class TeamController extends RESTController<Team, TeamRepository, TeamService> {

    @Autowired
    private TeamService teamService;

    @Override
    @JsonView(View.External.class)
    public List<Team> getAll() {
        return super.getAll();
    }

    @Override
    public Team update(@RequestBody Team team) throws JsonProcessingException {
        return teamService.updateTeam(team);
    }

    @PostMapping(value="/delete",produces = {"application/json"})
    public ResponseEntity<Object> deleteTeam(@RequestBody Team team){
        teamService.deleteTeam(team);
        return ResponseEntity.ok().body("{\"teamDeleted\":\"true\"}");
    }

    @PostMapping(value="/addMember/{teamId}",produces = {"application/json"})
    public Team addMember(@RequestBody Member member, @PathVariable Long teamId) throws JsonProcessingException {
        return teamService.addMember(teamId,member);
    }

    @PostMapping(value="/removeMember/{teamId}",produces = {"application/json"})
    public Team removeMemberFromTeam(@RequestBody Member member,@PathVariable Long teamId) throws JsonProcessingException {
        return teamService.removeMemberFromTeam(teamId,member);
    }
}
