package de.cschillingtschuehly.gcwebx.repositories;

import de.cschillingtschuehly.gcwebx.modell.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository  extends CrudRepository<Team,Long> {
    List<Team> findAll();
}
