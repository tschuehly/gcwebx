package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public String getMemberTable(){

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        List<Member> memberList = memberRepository.findAll();
        ArrayNode memberTable = mapper.valueToTree(memberList);
        return memberTable.toString();
    }
}
