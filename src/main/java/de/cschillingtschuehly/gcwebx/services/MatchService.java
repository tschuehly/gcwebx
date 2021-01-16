package de.cschillingtschuehly.gcwebx.services;

import de.cschillingtschuehly.gcwebx.helpers.CRUDService;
import de.cschillingtschuehly.gcwebx.modell.Match;
import de.cschillingtschuehly.gcwebx.repositories.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService extends CRUDService<Match, MatchRepository> {
}
