package com.finshot.web.mapper;


import com.finshot.web.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper {


    int inputData(LoginVO lvo);

    int checkId(String userid);

    LoginVO readOneMember(LoginVO lvo);

    String checkEncodedPassword(String userid);

    int changeNick(LoginVO lvo);

    int changePassword(LoginVO lvo);

    LoginVO getUserData(String userid);
}
