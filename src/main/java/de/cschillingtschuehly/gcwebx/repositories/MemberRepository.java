package de.cschillingtschuehly.gcwebx.repositories;

import de.cschillingtschuehly.gcwebx.modell.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByTeamspeakId(String teamspeakId);
    List<Member> findAll();


}
