package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.services.MemberService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin()
public class WebController {
    @Autowired
    private MemberService memberService;
    @CrossOrigin()
    @GetMapping(value = "/getMemberTable",produces = {"application/json"})
    public ResponseEntity getMember(){
        String memberTable = memberService.getMemberTable();
        return ResponseEntity.ok().body(memberTable);
    }
    @PutMapping(value="/updateMember",produces = {"application/json"})
    public ResponseEntity editMember(@RequestBody Member member) throws JsonProcessingException {
        String updatedMember = memberService.updateMember(member);
        return ResponseEntity.ok().body(updatedMember);
    }
    @PostMapping(value="/createMember",produces = {"application/json"})
    public ResponseEntity createMember(@RequestBody Member member) throws JsonProcessingException {
        String createdMember = memberService.createMember(member);
        return ResponseEntity.ok().body(createdMember);
    }
    @DeleteMapping(value="/deleteMember",produces = {"application/json"})
    public ResponseEntity deleteMember(@RequestBody Member member) throws JsonProcessingException {
        memberService.deleteMember(member);
        return ResponseEntity.ok().body("{\"memberDeleted\":\"true\"}");
    }
}
