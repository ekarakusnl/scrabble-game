package com.gamecity.scrabble.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * <code>User</code> represents a real user being in scrabble platform which is known by its
 * <code>username</code>. A <code>user</code> could have capabilities such as login, create game,
 * join game, watch game based on his {@link UserAuthority} capabilities.
 * 
 * @author ekarakus
 *
 */
@Entity
@Table(name = "users")
@SuppressWarnings("serial")
public class User extends AbstractBaseEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "account_non_expired", nullable = false, columnDefinition = "tinyint default 1")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = false, columnDefinition = "tinyint default 1")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", nullable = false, columnDefinition = "tinyint default 1")
    private boolean credentialsNonExpired = true;

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private boolean enabled = true;

    @Transient
    private Set<UserAuthority> authorities;

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email adress of the user
     * 
     * @return unique email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * Sets the accountNonExpired flag of the user
     * 
     * @param accountNonExpired
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * Sets the accountNonLocked flag of the user
     * 
     * @param accountNonLocked
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * Sets the credentialsNonExpired flag of the user
     * 
     * @param credentialsNonExpired
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled flag of the user
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Set the given authorities for the user
     * 
     * @param authorities
     */
    public void setAuthorities(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof User)) {
            return false;
        }

        final User user = (User) object;
        return this.username.equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.username);
    }

}
