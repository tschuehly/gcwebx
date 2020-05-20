package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    @GetMapping(value="/updateMember",produces = {"application/json"}) //TODO: Make POST
    public ResponseEntity editMember(@RequestParam(value="memberId") Long memberId,
                                     @RequestParam(value="name",required = false) String name,
                                     @RequestParam(value="teamspeakId",required = false) String teamspeakId,
                                     @RequestParam(value="generalInfo",required = false) String generalInfo,
                                     @RequestParam(value="dateOfBirth",required = false) LocalDate dateOfBirth,
                                     @RequestParam(value="desblTeam",required = false) String desblTeam,
                                     @RequestParam(value="joinDate",required = false) LocalDate joinDate,
                                     @RequestParam(value="acceptanceDate",required = false) LocalDate acceptanceDate,
                                     @RequestParam(value="editor",required = false) String editor,
                                     @RequestParam(value="warnings",required = false) Integer warnings,
                                     @RequestParam(value="uplayId",required = false) String uplayId) throws JsonProcessingException {
        String updatedMember = memberService.updateMember(memberId, name, teamspeakId, generalInfo, dateOfBirth, desblTeam, joinDate, acceptanceDate, editor,warnings,uplayId);
        return ResponseEntity.ok().body(updatedMember);
    }
    @RequestMapping(value="/findMember",method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity findMember(@RequestParam(value="memberId") String memberId){
        String returnBody = "{hello:true}";
        return ResponseEntity.ok().body(returnBody);
    }
}
