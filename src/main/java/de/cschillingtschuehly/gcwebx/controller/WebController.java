package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.User;
import de.cschillingtschuehly.gcwebx.services.MemberService;
import de.cschillingtschuehly.gcwebx.services.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins="*",allowedHeaders = "*")
public class WebController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private UserService userService;
    @CrossOrigin()
    @GetMapping(value = "/getMembers",produces = {"application/json"})
    public ResponseEntity getMember(){
        String memberTable = memberService.getMemberTable();
        return ResponseEntity.ok().body(memberTable);
    }
    @PutMapping(value="/updateMember",produces = {"application/json"})
    public ResponseEntity editMember(@RequestBody Member member) throws JsonProcessingException {
        System.out.println(member);

        String updatedMember = memberService.updateMember(member);
        return ResponseEntity.ok().body(updatedMember);
    }
    @PostMapping(value="/createMember",produces = {"application/json"})
    public ResponseEntity createMember(@RequestBody Member member) throws JsonProcessingException {
        System.out.println(member);
        String createdMember = memberService.createMember(member);
        return ResponseEntity.ok().body(createdMember);
    }
    @DeleteMapping(value="/deleteMember",produces = {"application/json"})
    public ResponseEntity deleteMember(@RequestBody Member member) throws JsonProcessingException {
        memberService.deleteMember(member);
        return ResponseEntity.ok().body("{\"memberDeleted\":\"true\"}");
    }

    /*@GetMapping("/login")
    public String showRegistrationForm(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }*/
    @CrossOrigin(origins="*",allowedHeaders = "*")
    @PostMapping("/login")
    public void createAccount(@RequestBody User user) {
        userService.createUser(user);
    }
}
