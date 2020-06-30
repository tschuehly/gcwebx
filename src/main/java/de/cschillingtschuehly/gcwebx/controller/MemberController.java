package de.cschillingtschuehly.gcwebx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.cschillingtschuehly.gcwebx.modell.Member;
import de.cschillingtschuehly.gcwebx.modell.User;
import de.cschillingtschuehly.gcwebx.services.MemberService;
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
    @GetMapping(value = "/admin/getMembers",produces = {"application/json"})
    public ResponseEntity getMember(){
        String memberTable = memberService.getMemberTable();
        return ResponseEntity.ok().body(memberTable);
    }
    @PutMapping(value="/admin/updateMember",produces = {"application/json"})
    public ResponseEntity editMember(@RequestBody Member member) throws JsonProcessingException {
        System.out.println(member);

        String updatedMember = memberService.updateMember(member);
        return ResponseEntity.ok().body(updatedMember);
    }
    @PostMapping(value="/admin/createMember",produces = {"application/json"})
    public ResponseEntity createMember(@RequestBody Member member) throws JsonProcessingException {
        System.out.println(member);
        String createdMember = memberService.createMember(member);
        return ResponseEntity.ok().body(createdMember);
    }
    @PostMapping(value="/admin/deleteMember",produces = {"application/json"})
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

    @GetMapping(value = "/admin/getUsers",produces = {"application/json"})
    public ResponseEntity getUsers(){
        String userTable = userService.getUserTable();
        return ResponseEntity.ok().body(userTable);
    }
    @PutMapping(value="/admin/updateUser",produces = {"application/json"})
    public ResponseEntity editUser(@RequestBody User user) throws JsonProcessingException {
        String updatedUser = userService.updateUser(user);
        return ResponseEntity.ok().body(updatedUser);
    }
    @PostMapping(value="/admin/createUser",produces = {"application/json"})
    public ResponseEntity createUser(@RequestBody User user) throws JsonProcessingException {
        String createdUser = userService.createUser(user);
        return ResponseEntity.ok().body(createdUser);
    }
    @PostMapping(value="/admin/deleteUser",produces = {"application/json"})
    public ResponseEntity deleteUser(@RequestBody User user) throws JsonProcessingException {
        userService.deleteUser(user);
        return ResponseEntity.ok().body("{\"userDeleted\":\"true\"}");
    }
    //@CrossOrigin("http://localhost:4200/*")
    @RequestMapping("/user")
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }
    @GetMapping("/getRole/{username}")
    public ResponseEntity getRole(@PathVariable String username){
        return ResponseEntity.ok().body("{\n \"roles\": \n" + userService.getRolesByUsername(username)+ "\n}");
    }



}
