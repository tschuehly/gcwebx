package de.cschillingtschuehly.gcwebx.controller;

import de.cschillingtschuehly.gcwebx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin()
public class WebController {
    @Autowired
    private UserService userService;

    @RequestMapping("/api/login")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Object> logout(){
        return ResponseEntity.ok().body("Successfully logged out");
    }

    @GetMapping("/api/getRole/{username}")
    public ResponseEntity<Object> getRole(@PathVariable String username){
        return ResponseEntity.ok().body("{\n \"roles\": \n" + userService.getRolesByUsername(username)+ "\n}");
    }

}
