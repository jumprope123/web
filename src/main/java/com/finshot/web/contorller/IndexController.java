package com.finshot.web.contorller;

import com.finshot.web.service.WriteServiceImpl;
import com.finshot.web.vo.LoginVO;
import com.finshot.web.vo.WriteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexController {


    @Autowired
    private WriteServiceImpl wsrv;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/write")
    public ModelAndView write(ModelAndView mv, HttpSession session){
        mv.setViewName("/write");
        LoginVO lvo = (LoginVO) session.getAttribute("UID");
        mv.addObject("UID",lvo);
        return mv;
    }

    @PostMapping("/writeOk")
    public String writeok(WriteVO wvo){
        if (wvo.getReadonlymember() == null){
            wvo.setReadonlymember(false);
        }
        int cnt = wsrv.inputData(wvo);
        return "redirect:/list?cp=1";
    }

    @GetMapping("/list")
    public ModelAndView list (ModelAndView mv, String cp ,HttpSession session){
        LoginVO lvo = (LoginVO) session.getAttribute("UID");

        if (cp == null || cp.isEmpty()) { cp = "1"; }

        mv.setViewName("/list");
        mv.addObject("data",wsrv.readAllData(cp));
        mv.addObject("dataCnt", wsrv.readAllDataCnt());
        mv.addObject("UID",lvo);

        return mv;
    }

    @GetMapping("/find")
    public ModelAndView find (ModelAndView mv, String findtype, String findkey, String cp, HttpSession session){
        LoginVO lvo = (LoginVO) session.getAttribute("UID");

        if (cp == null || cp.isEmpty()) { cp = "1"; }

        mv.setViewName("/list");
        mv.addObject("data",wsrv.readFindData(findtype,findkey,cp));
        mv.addObject("dataCnt",wsrv.readFindCnt(findtype,findkey));
        mv.addObject("UID",lvo);

        return mv;
    }

    @GetMapping("/view")
    public ModelAndView view(ModelAndView mv ,String boardno, HttpSession session){
        LoginVO lvo = (LoginVO) session.getAttribute("UID");

        mv.setViewName("/view");
        mv.addObject("data", wsrv.readOneData(boardno));
        wsrv.addViewCtn(boardno); //뷰카운트
        mv.addObject("UID",lvo);

        return mv;
    }

    @ResponseBody
    @GetMapping("/modicheck")
    public void modicheck(String boardno, String password, HttpServletResponse res){
        Boolean passwdCheck = wsrv.passwdCheck(boardno,password);
        res.setContentType("application/text; charset=UTF-8");
        try {
            res.getWriter().print(passwdCheck);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/modiData")
    public ModelAndView modiData(ModelAndView mv , String boardno){
        mv.setViewName("/modiView");
        mv.addObject("data", wsrv.readOneData(boardno));
        return mv;
    }

    @PostMapping("modiOk")
    public String modiOk(WriteVO wvo){
        int cnt = wsrv.modiData(wvo);
        return "redirect:/list?cp=1";
    }

    @GetMapping("delData")
    public String delData(String boardno){
        wsrv.delOneData(boardno);
        return "redirect:/list?cp=1";
    }

}
