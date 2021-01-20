package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.helpers.RESTController;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.View;
import de.cschillingtschuehly.gcwebx.repositories.MemberRepository;
import de.cschillingtschuehly.gcwebx.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/member")
public class MemberController extends RESTController<Member, MemberRepository, MemberService> {

    @Autowired
    private MemberService memberService;

    @Override
    public Member update(@RequestBody Member member) throws JsonProcessingException {
        return memberService.updateMember(member);
    }

    @PostMapping(value="/delete",produces = {"application/json"})
    public ResponseEntity<Object> deleteMember(@RequestBody Member member){
        memberService.deleteMember(member);
        return ResponseEntity.ok().body("{\"memberDeleted\":\"true\"}");
    }

    @GetMapping(value = "/getStreamer",produces = {"application/json"})
    @JsonView(value = View.External.class)
    public List<Member> getStreamer(){
        return memberService.getStreamer();
    }

    @GetMapping(value = "/getYoutuber",produces = {"application/json"})
    @JsonView(value = View.External.class)
    public List<Member> getYoutuber(){
        return memberService.getYoutuber();
    }

}
