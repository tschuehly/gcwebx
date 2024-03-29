package de.cschillingtschuehly.gcwebx.modell;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthenticatedWebsiteUser extends WebsiteUser implements UserDetails {

    public AuthenticatedWebsiteUser(WebsiteUser user){
        super(user.getUsername(),user.getPassword(),user.isRoleUser(),user.isRoleEditor(),user.isRoleSupport(),user.isRoleModerator(),user.isRoleAdmin());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(getRolesAsCSV());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
