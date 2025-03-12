package com.example.ecommer.Config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ecommer.model.UserDtls;

public class CustomUser implements UserDetails {

    private final UserDtls userDetails;

    public CustomUser(UserDtls user) {
        this.userDetails = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Granting the role of the user
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userDetails.getRole());
        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        // Returning the username or email of the user
        return userDetails.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Account is always considered non-expired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Account is always considered non-locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Credentials are always considered non-expired
        return true;
    }

    @Override
    public boolean isEnabled() {
     return userDetails.getIsEnable();
    }
}
