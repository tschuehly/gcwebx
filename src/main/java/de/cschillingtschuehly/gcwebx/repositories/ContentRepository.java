package de.cschillingtschuehly.gcwebx.repositories;

import de.cschillingtschuehly.gcwebx.modell.Content;
import de.cschillingtschuehly.gcwebx.modell.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContentRepository extends CrudRepository<Content, Integer> {

    List<Content> findAll();
}
