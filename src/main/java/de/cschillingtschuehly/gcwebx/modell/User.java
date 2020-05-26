package de.cschillingtschuehly.gcwebx.modell;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    //public User(String username, String password){
        //this.username = username;
        //this.password = password;
    //}
}
