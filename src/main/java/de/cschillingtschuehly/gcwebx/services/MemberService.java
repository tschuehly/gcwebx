package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private TeamService teamService;
    public String getMemberTable(){
        ObjectMapper mapper = mapperService.jacksonMapper();
        List<Member> memberList = memberRepository.findAll();
        ArrayNode memberTable = mapper.valueToTree(memberList);
        return memberTable.toString();
    }
    public String updateMember(Member memberParam) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        Mapper dozerMapper = mapperService.dozerMapper();
        Member member = memberRepository.findById(memberParam.getMemberId()).get();
        dozerMapper.map(memberParam,member);
        memberRepository.save(member);
        return jacksonMapper.writeValueAsString(member);
    }

    public String createMember(Member memberParam) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        memberRepository.save(memberParam);
        Member member = memberRepository.findById(memberParam.getMemberId()).get();
        return jacksonMapper.writeValueAsString(member);
    }
    public void deleteMember(Member memberParam){
        System.out.println(memberParam);
        teamService.removeMember(memberParam);
        memberRepository.delete(memberParam);
        return;

    }
    public List<Member> getStreamer(){
        return memberRepository.findAll().stream().filter(member -> member.getTwitch() != null).filter(member -> member.getDeleted() != null && member.getDeleted() != true).collect(Collectors.toList());
        //TODO: Remove unwanted info
    }
}
