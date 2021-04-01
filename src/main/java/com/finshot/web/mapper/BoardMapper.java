package com.finshot.web.mapper;

import com.finshot.web.vo.WriteVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardMapper {

    int delOneData(int boardno);

    int inputData(WriteVO wvo);

    List<WriteVO> readAllData(int snum);

    int readAllDataCnt();

    List<WriteVO> readFindData(Map<String, Object> map);

    int readFindCnt(Map<String, Object> map);

    WriteVO readOneData(int boardnoForInt);

    String passwdCheck(int parseInt);

    int modiData(WriteVO wvo);

    void addViewCnt(int parseInt);
}
