package de.cschillingtschuehly.gcwebx.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.cschillingtschuehly.gcwebx.modell.WebsiteUser;
import de.cschillingtschuehly.gcwebx.modell.AuthenticatedWebsiteUser;
import de.cschillingtschuehly.gcwebx.repositories.UserRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapperService mapperService;
    public String getUserTable(){
        ObjectMapper mapper = mapperService.jacksonMapper();
        List<WebsiteUser> userList = userRepository.findAll();
        ArrayNode userTable = mapper.valueToTree(userList);
        userTable.forEach( jsonNode -> {
            ObjectNode objNode = (ObjectNode) jsonNode;
            objNode.remove("password");
        });
        return userTable.toString();
    }
    @Override
    public UserDetails loadUserByUsername(String username) {
        WebsiteUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new AuthenticatedWebsiteUser(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            role.getRoleValue().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getCanonicalName()))
                    .forEach(authorities::add);
        }
        System.out.println(authorities);

        return authorities;
    }
    public String getRolesByUsername(String username){
        WebsiteUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return user.getRoles();
    }
    public String updateUser(WebsiteUser userParam) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        Mapper dozerMapper = mapperService.dozerMapper();

        WebsiteUser user = userRepository.findById(userParam.getId()).get();
        if(userParam.getPassword() == null){
            userParam.setPassword(user.getPassword());
        }
        dozerMapper.map(userParam,user);
        userRepository.save(user);
        return jacksonMapper.writeValueAsString(user);
    }
    public String createUser(WebsiteUser userParam) throws JsonProcessingException {
        ObjectMapper jacksonMapper = mapperService.jacksonMapper();
        userRepository.save(userParam);
        WebsiteUser user = userRepository.findById(userParam.getId()).get();
        return jacksonMapper.writeValueAsString(user);
    }
    public void deleteUser(WebsiteUser userParam){
        userRepository.delete(userParam);

    }
}
