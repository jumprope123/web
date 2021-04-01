package com.finshot.web.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class LoginVO implements UserDetails {

    private String userid;
    private String nickname;
    private String password;
    private String passwordcheck;
    private String role;

    public LoginVO() {
    }

    public LoginVO(String userid, String nickname, String password, String passwordcheck) {
        this.userid = userid;
        this.nickname = nickname;
        this.password = password;
        this.passwordcheck = passwordcheck;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userid;
    }


    @Override
    public String getPassword() {
        return password;
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
