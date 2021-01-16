package de.cschillingtschuehly.gcwebx.controller;

import de.cschillingtschuehly.gcwebx.helpers.RESTController;
import de.cschillingtschuehly.gcwebx.modell.WebsiteUser;
import de.cschillingtschuehly.gcwebx.repositories.UserRepository;
import de.cschillingtschuehly.gcwebx.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/api/user")
public class UserController extends RESTController<WebsiteUser, UserRepository,UserService> {

}
