package de.cschillingtschuehly.gcwebx.modell;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "username is required")
    @Column(nullable = false, unique = true)
    private String username;
    private String email;
    @NotEmpty(message = "password is required")
    private String password;
    @NotEmpty(message = "role is required")
    private String roles;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getRoles() {

        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    public User(){

    }
    public User(@NotEmpty(message = "username is required") String username, @NotEmpty(message = "password is required") String password, @NotEmpty(message = "role is required") String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    //public User(String username, String email, String password){
        //this.username = username;
        //this.email = email;
        //this.password = password;
    //}
}
