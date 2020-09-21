package de.cschillingtschuehly.gcwebx.repositories;

import de.cschillingtschuehly.gcwebx.modell.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match,Long> {
}
