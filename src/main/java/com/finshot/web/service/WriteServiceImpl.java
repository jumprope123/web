package com.finshot.web.service;

import com.finshot.web.mapper.BoardMapper;
import com.finshot.web.vo.WriteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WriteServiceImpl {

    @Autowired
    private BoardMapper mapper;


    public int inputData(WriteVO wvo) {
//        return wdao.inputData(wvo);
        return mapper.inputData(wvo);
    }


    public List<WriteVO> readAllData(String cp) {
        int snum = ((Integer.parseInt(cp) -1) * 10); //10명씩 데이터 가져옴
//        return wdao.readAllData(snum);
        return mapper.readAllData(snum);
    }


    public int readAllDataCnt() {
//        return wdao.readAllDataCnt();
         return mapper.readAllDataCnt();
    }


    public List<WriteVO> readFindData(String findtype, String findkey, String cp) {
        int snum = ((Integer.parseInt(cp) -1) * 10); //10명씩 데이터 가져옴

        Map<String,Object> map = new HashMap<>();
        map.put("findtype", findtype);
        map.put("findkey", findkey);
        map.put("snum", snum);

//        return wdao.readFindData(map);
        return mapper.readFindData(map);
    }


    public int readFindCnt(String findtype, String findkey) {
        Map<String,Object> map = new HashMap<>();
        map.put("findtype", findtype);
        map.put("findkey", findkey);
//        return wdao.readFindCnt(map);
        return mapper.readFindCnt(map);
    }


    public WriteVO readOneData(String boardno) {
        int boardnoForInt = Integer.parseInt(boardno);
//        return wdao.readOneData(boardnoForInt);
        return mapper.readOneData(boardnoForInt);
    }


    public Boolean passwdCheck(String boardno, String password) {
//        String realPasswd = wdao.passwdCheck(Integer.parseInt(boardno));
        String realPasswd = mapper.passwdCheck(Integer.parseInt(boardno));
        Boolean checkResult = realPasswd.equals(password); //비밀번호 같은지 체크
        return checkResult;
    }


    public int modiData(WriteVO wvo) {
//        return wdao.modiData(wvo);
        return mapper.modiData(wvo);
    }


    public void addViewCtn(String boardno) {
//        wdao.addViewCtn(Integer.parseInt(boardno));
        mapper.addViewCnt(Integer.parseInt(boardno));
    }


    public void delOneData(String boardno) {
        mapper.delOneData(Integer.parseInt(boardno));

    }
}
