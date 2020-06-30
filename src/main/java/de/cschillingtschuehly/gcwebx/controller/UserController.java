package de.cschillingtschuehly.gcwebx.controller;

import de.cschillingtschuehly.gcwebx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@CrossOrigin()
public class UserController {

    @Autowired
    private UserService userService;

    //@CrossOrigin("http://localhost:4200/*")
    @RequestMapping("/api/user")
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }

    ///@CrossOrigin()
    @GetMapping("/api/admin")
    public ResponseEntity getAdmin(){
        return ResponseEntity.ok().body("Hello Admin");
    }


    ///@CrossOrigin()
    @PostMapping("/api/logout")
    public ResponseEntity logout(){
        return ResponseEntity.ok().body("Successfully logged out");
    }
}
