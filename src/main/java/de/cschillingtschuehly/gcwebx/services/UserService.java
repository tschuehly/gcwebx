package de.cschillingtschuehly.gcwebx.services;

import de.cschillingtschuehly.gcwebx.modell.User;
import de.cschillingtschuehly.gcwebx.modell.AuthenticatedUser;
import de.cschillingtschuehly.gcwebx.repositories.UserRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new AuthenticatedUser(user);
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

    public void createUser(User user) {
        userRepository.save(user);
    }


}
