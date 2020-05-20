package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MapperService mapperService;
    public String getMemberTable(){
        ObjectMapper mapper = mapperService.mapper();
        List<Member> memberList = memberRepository.findAll();
        ArrayNode memberTable = mapper.valueToTree(memberList);
        return memberTable.toString();
    }
    public String updateMember(Long memberId,String name,String teamspeakId,String generalInfo,LocalDate dateOfBirth,String desblTeam,LocalDate joinDate,LocalDate acceptanceDate,String editor,Integer warnings,String uplayId) throws JsonProcessingException {
        ObjectMapper mapper = mapperService.mapper();
        Member member = memberRepository.findById(memberId).get();
        if(name != null){member.setName(name);}
        if(teamspeakId != null){member.setTeamspeakId(teamspeakId);}
        if(generalInfo != null){member.setGeneralInfo(generalInfo);}
        if(dateOfBirth != null){member.setDateOfBirth(dateOfBirth);}
        if(desblTeam != null){member.setDesblTeam(desblTeam);}
        if(joinDate != null){member.setJoinDate(joinDate);}
        if(acceptanceDate != null){member.setAcceptanceDate(acceptanceDate);}
        if(editor != null){member.setEditor(editor);}
        if(warnings != null){member.setWarnings(warnings);}
        if(uplayId != null){member.setUplayId(uplayId);} //TODO:Besseren Style
        memberRepository.save(member);
        return mapper.writeValueAsString(member);
    }
}
