package de.cschilingtschuehly.gcwebx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class WebController {
    @RequestMapping(value="/getMember",method = RequestMethod.GET,produces = {"application/json"})
    public ResponseEntity getMember(@RequestParam(value="memberId") String memberId){
        String returnBody = "{hello:true}";
        return ResponseEntity.ok().body(returnBody);
    }
}
