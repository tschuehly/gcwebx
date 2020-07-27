package de.cschillingtschuehly.gcwebx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ViewController {
    @RequestMapping({ "/home", "/esport/**", "/memberTable", "/teamspeak", "/login", "/edit", "/userTable", "/news","/logo" })
    public String index() {
        return "forward:/index.html";
    }
}
