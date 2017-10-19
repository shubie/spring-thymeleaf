package com.codekraft.cdfipb.recruitementfrontend.domains;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Setter
public class User implements UserDetails {

    private String username;
    private String password;
    private String passwordConfirmation;
    private String role;

    public User(){}

    public User(String username, String password, String fullname, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> gt = new HashSet<>();
        gt.add(new SimpleGrantedAuthority(this.getRole()));
        return gt;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
