package com.gamecity.scrabble.entity;

import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

/**
 * <code>Authority</code> represents the set of capabilites of a {@link User} in scrabble platform.
 * 
 * @author ekarakus
 *
 */
@SuppressWarnings("serial")
public class UserAuthority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * Sets the authority of the user
     * 
     * @param authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof UserAuthority)) {
            return false;
        }

        final UserAuthority userAuthority = (UserAuthority) object;
        return this.authority.equals(userAuthority.getAuthority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.authority);
    }

}
