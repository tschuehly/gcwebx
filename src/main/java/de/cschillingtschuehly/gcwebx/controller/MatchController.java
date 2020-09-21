package de.cschillingtschuehly.gcwebx.controller;

import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Match;
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
    public ResponseEntity createMatch(@RequestBody Match match){
        System.out.println(match);
        Match createdMatch = matchService.createMatch(match);
        return ResponseEntity.ok().body(createdMatch);
    }
    @GetMapping(value = "/api/getMatches",produces = {"application/json"})
    public ResponseEntity getMatches(){
        List<Match> contentList = matchService.getMatches();
        System.out.println(contentList);
        return ResponseEntity.ok().body(contentList);
    }
}
