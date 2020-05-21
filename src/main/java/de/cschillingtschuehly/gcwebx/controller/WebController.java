package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.services.MemberService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
public class WebController {
    @Autowired
    private MemberService memberService;
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
    @RequestMapping(value="/findMember",method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity findMember(@RequestParam(value="memberId") String memberId){
        String returnBody = "{hello:true}";
        return ResponseEntity.ok().body(returnBody);
    }
}
