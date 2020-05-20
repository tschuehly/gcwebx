package de.cschillingtschuehly.gcwebx.controller;

import de.cschillingtschuehly.gcwebx.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class WebController {
    @Autowired
    private MemberService memberService;
    @RequestMapping(value="/getMemberTable",method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity getMember(){
        String memberTable = memberService.getMemberTable();
        return ResponseEntity.ok().body(memberTable);
    }
    @RequestMapping(value="/findMember",method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity findMember(@RequestParam(value="memberId") String memberId){
        String returnBody = "{hello:true}";
        return ResponseEntity.ok().body(returnBody);
    }
}
