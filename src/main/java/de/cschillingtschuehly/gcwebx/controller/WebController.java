package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.User;
import de.cschillingtschuehly.gcwebx.services.MemberService;
import de.cschillingtschuehly.gcwebx.services.TeamService;
import de.cschillingtschuehly.gcwebx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin()
public class WebController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;


    @GetMapping(value = "/api/getMembers",produces = {"application/json"})
    public ResponseEntity getMember(){
        String memberTable = memberService.getMemberTable();
        return ResponseEntity.ok().body(memberTable);
    }
    @PutMapping(value="/api/updateMember",produces = {"application/json"})
    public ResponseEntity editMember(@RequestBody Member member) throws JsonProcessingException {
        System.out.println(member);

        String updatedMember = memberService.updateMember(member);
        return ResponseEntity.ok().body(updatedMember);
    }
    @PostMapping(value="/api/createMember",produces = {"application/json"})
    public ResponseEntity createMember(@RequestBody Member member) throws JsonProcessingException {
        System.out.println(member);
        String createdMember = memberService.createMember(member);
        return ResponseEntity.ok().body(createdMember);
    }
    @PostMapping(value="/api/deleteMember",produces = {"application/json"})
    public ResponseEntity deleteMember(@RequestBody Member member) throws JsonProcessingException {
        memberService.deleteMember(member);
        return ResponseEntity.ok().body("{\"memberDeleted\":\"true\"}");
    }

    /*
    @GetMapping("/login")
    public String showRegistrationForm(WebRequest request, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }*/

    @GetMapping(value = "/api/getUsers",produces = {"application/json"})
    public ResponseEntity getUsers(){
        String userTable = userService.getUserTable();
        return ResponseEntity.ok().body(userTable);
    }
    @PutMapping(value="/api/updateUser",produces = {"application/json"})
    public ResponseEntity editUser(@RequestBody User user) throws JsonProcessingException {
        String updatedUser = userService.updateUser(user);
        return ResponseEntity.ok().body(updatedUser);
    }
    @PostMapping(value="/api/createUser",produces = {"application/json"})
    public ResponseEntity createUser(@RequestBody User user) throws JsonProcessingException {
        String createdUser = userService.createUser(user);
        return ResponseEntity.ok().body(createdUser);
    }
    @PostMapping(value="/api/deleteUser",produces = {"application/json"})
    public ResponseEntity deleteUser(@RequestBody User user) throws JsonProcessingException {
        userService.deleteUser(user);
        return ResponseEntity.ok().body("{\"userDeleted\":\"true\"}");
    }

    @GetMapping("/api/getRole/{username}")
    public ResponseEntity getRole(@PathVariable String username){
        return ResponseEntity.ok().body("{\n \"roles\": \n" + userService.getRolesByUsername(username)+ "\n}");
    }

    @GetMapping("/api/getTeams")
    public ResponseEntity getTeams(){
        return ResponseEntity.ok().body(teamService.getTeams());

    }


}
