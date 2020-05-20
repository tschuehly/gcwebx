package de.cschillingtschuehly.gcwebx.repositories;

import de.cschillingtschuehly.gcwebx.modell.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member,Long> {
    List<Member> findByTeamspeakId(String teamspeakId);
    List<Member> findAll();

}
