package de.cschillingtschuehly.gcwebx.modell;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@JsonView(View.Internal.class)
public class WebsiteUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String email;
    private String password;
    private boolean roleUser;
    private boolean roleEditor;
    private boolean roleSupport;
    private boolean roleModerator;
    private boolean roleAdmin;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public WebsiteUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getRolesAsCSV(){
        String returnString = "";
        if(roleUser){
            returnString = returnString + "ROLE_USER,";
        }
        if(roleEditor){
            returnString = returnString + "ROLE_EDITOR,";
        }
        if(roleSupport){
            returnString = returnString + "ROLE_SUPPORT,";
        }
        if(roleModerator){
            returnString = returnString + "ROLE_MODERATOR,";
        }
        if(roleAdmin){
            returnString = returnString + "ROLE_ADMIN,";
        }
        return returnString;

    }
    public String getRoles() {
        String returnString = "{\n \"roleUser\":"+this.roleUser + ",\"roleEditor\":"+this.roleEditor + ",\"roleSupport\":"+this.roleSupport +",\"roleModerator\":"+this.roleModerator +",\"roleAdmin\":"+this.roleAdmin+"\n}";
        return returnString;
    }

    public WebsiteUser(){

    }

    public WebsiteUser(@NotEmpty(message = "username is required") String username, @NotEmpty(message = "password is required") String password, boolean role_user, boolean role_editor, boolean role_support, boolean role_moderator, boolean role_admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleUser = role_user;
        this.roleEditor = role_editor;
        this.roleSupport = role_support;
        this.roleModerator = role_moderator;
        this.roleAdmin = role_admin;
    }
    //public User(String username, String email, String password){
        //this.username = username;
        //this.email = email;
        //this.password = password;
    //}
}
