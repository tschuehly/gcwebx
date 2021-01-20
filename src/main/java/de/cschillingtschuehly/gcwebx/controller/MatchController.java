package de.cschillingtschuehly.gcwebx.controller;

import de.cschillingtschuehly.gcwebx.helpers.RESTController;
import de.cschillingtschuehly.gcwebx.modell.Match;
import de.cschillingtschuehly.gcwebx.repositories.MatchRepository;
import de.cschillingtschuehly.gcwebx.services.MatchService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/match")
public class MatchController extends RESTController<Match, MatchRepository,MatchService> {
}
