package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Match;
import de.cschillingtschuehly.gcwebx.modell.View;
import de.cschillingtschuehly.gcwebx.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MatchController {
    @Autowired
    private MatchService matchService;

    @PostMapping(value = "/api/createMatch",produces = {"application/json"})
    public ResponseEntity createMatch(@RequestBody Match match) throws JsonProcessingException {
        Match createdMatch = matchService.createMatch(match);
        return ResponseEntity.ok().body(createdMatch);
    }
    @GetMapping(value = "/api/getMatches",produces = {"application/json"})
    @JsonView(value = View.External.class)
    public ResponseEntity getMatches(){
        List<Match> contentList = matchService.getMatches();
        return ResponseEntity.ok().body(contentList);
    }

    @PostMapping(value = "/api/getMatchByID",produces = {"application/json"})
    @JsonView(value = View.External.class)
    public ResponseEntity getMatchById(@RequestBody Integer id){
        Match match = matchService.getMatchById(id);
        return ResponseEntity.ok().body(match);
    }

    @PutMapping(value="/api/updateMatch",produces = {"application/json"})
    public ResponseEntity editMatch(@RequestBody Match match) throws JsonProcessingException {
        Match updatedMatch = matchService.updateMatch(match);
        return ResponseEntity.ok().body(updatedMatch);
    }
    @DeleteMapping(value="/api/deleteMatch/{matchId}",produces = {"application/json"})
    public ResponseEntity deleteMatch(@PathVariable Integer matchId) throws JsonProcessingException {
        matchService.deleteMatch(matchId);
        return ResponseEntity.ok().body("{\"matchDeleted\":\"true\"}");
    }

}
