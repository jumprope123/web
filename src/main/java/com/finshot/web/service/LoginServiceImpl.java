package com.finshot.web.service;

import com.finshot.web.mapper.LoginMapper;
import com.finshot.web.vo.LoginVO;
import com.finshot.web.vo.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private LoginMapper mapper;


    public int inputJoinData(LoginVO lvo) {
        return mapper.inputData(lvo);
    }

    public int idCheck(LoginVO lvo) {
        return mapper.checkId(lvo.getUserid());
    }

    //public LoginVO login(LoginVO lvo) {
//        return mapper.readOneMember(lvo);
//    }


    public LoginVO readOneMember(LoginVO lvo) {
        return mapper.readOneMember(lvo);
    }

    public String checkEncodedPassword(String userid) {
        return mapper.checkEncodedPassword(userid);
    }

    public int changeNick(LoginVO lvo) {
        return mapper.changeNick(lvo);
    }

    public int updatePassword(LoginVO lvo) {
        return mapper.changePassword(lvo);
    }

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        //LoginVO lvo = mapper.getUserData(userid);
        return mapper.getUserData(userid);

    }
}
