package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Match;
import de.cschillingtschuehly.gcwebx.repositories.ContentRepository;
import de.cschillingtschuehly.gcwebx.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    private MapperService mapperService;

    public List<Match> getMatches(){
        List<Match> matchList = matchRepository.findAll();
        return matchList;
    }

    public Match getMatchById(int id){
        return matchRepository.findById(id).get();
    }
    public Match updateMatch(Match pmatch) throws JsonProcessingException {
        matchRepository.save(pmatch);
        return pmatch;
    }

    public Match createMatch(Match match) throws JsonProcessingException {
        matchRepository.save(match);
        return matchRepository.findById(match.getId()).get();
    }
    public void deleteMatch(Integer matchId){
        matchRepository.deleteById(matchId);

    }
}
