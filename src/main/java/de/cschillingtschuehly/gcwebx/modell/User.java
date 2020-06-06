package de.cschillingtschuehly.gcwebx.modell;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public User(){

    }
    public User(@NotEmpty(message = "username is required") String username, @NotEmpty(message = "password is required") String password, @NotEmpty(message = "role is required") String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    //public User(String username, String email, String password){
        //this.username = username;
        //this.email = email;
        //this.password = password;
    //}
}
