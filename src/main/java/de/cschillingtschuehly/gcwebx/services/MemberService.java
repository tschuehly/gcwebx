package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.cschillingtschuehly.gcwebx.helpers.CRUDService;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService extends CRUDService<Member,MemberRepository> {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private TeamService teamService;

    public Member updateMember(Member memberParam) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        Member member = memberRepository.findById(memberParam.getMemberId())
                .orElseThrow(EntityNotFoundException::new);
        jacksonMapper.readerForUpdating(member).readValue(jacksonMapper.writeValueAsString(memberParam));
        return memberRepository.save(member);
    }

    public void deleteMember(Member memberParam){
        teamService.removeMember(memberParam);
        memberRepository.delete(memberParam);
    }

    public List<Member> getStreamer(){
        return memberRepository.findAll().stream()
                .filter(member -> member.getTwitch() != null && !member.getTwitch().equals(""))
                .filter(member -> member.getDeleted() != null && !member.getDeleted()).collect(Collectors.toList());
    }
}
