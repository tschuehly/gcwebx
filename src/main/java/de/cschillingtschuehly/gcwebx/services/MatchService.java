package de.cschillingtschuehly.gcwebx.services;

import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Match;
import de.cschillingtschuehly.gcwebx.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    @Autowired
    MatchRepository matchRepository;
    public Match createMatch(Match pMatch){
        System.out.println(pMatch);
        Match match = matchRepository.save(pMatch);
        return match;
    }
    public List<Match> getMatches(){
        List<Match> matchList = matchRepository.findAll();
        System.out.println(matchList);
        return matchList;
    }
}
