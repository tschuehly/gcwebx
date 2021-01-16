package de.cschillingtschuehly.gcwebx.services;

import de.cschillingtschuehly.gcwebx.helpers.CRUDService;
import de.cschillingtschuehly.gcwebx.modell.AuthenticatedWebsiteUser;
import de.cschillingtschuehly.gcwebx.modell.WebsiteUser;
import de.cschillingtschuehly.gcwebx.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CRUDService<WebsiteUser,UserRepository> implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        WebsiteUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return new AuthenticatedWebsiteUser(user);
    }

    public String getRolesByUsername(String username) {
        WebsiteUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        return user.getRoles();
    }
}
