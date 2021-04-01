package com.finshot.web.vo;


import lombok.Data;

@Data
public class WriteVO {
    private int boardno;
    private String username;
    private String password;
    private String title;
    private String textforwrite;
    private int readcnt;
    private String regdate;
    private String modidate;
    private Boolean activate;
    private String userid;
    private Boolean readonlymember;

    public WriteVO() {
    }

}