package de.cschillingtschuehly.gcwebx.modell;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @NotNull
    @NotEmpty
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;

    //public User(String username, String email, String password){
        //this.username = username;
        //this.email = email;
        //this.password = password;
    //}
}
