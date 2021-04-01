package com.finshot.web.contorller;

import com.finshot.web.service.LoginServiceImpl;
import com.finshot.web.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private LoginServiceImpl lsrv;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login/join")
    public String join(){
        System.out.println("조인페이지");
        return "login/join";
    }

    @PostMapping("/login/joinOK")
    public String joinOK(LoginVO lvo){
        System.out.println("회원가입페이지");
        int result = lsrv.idCheck(lvo);
        String returnPage = "";

        if (result >= 1) {
            returnPage = "redirect:/login/fail";
        }else if(result == 0){
            String inputPassword = lvo.getPassword();
            String encodedPassword = passwordEncoder.encode(inputPassword);
            lvo.setPassword(encodedPassword);
            lvo.setPasswordcheck(encodedPassword);
            lsrv.inputJoinData(lvo);
            returnPage = "redirect:/login/success";
            System.out.println(encodedPassword);
        }
        return returnPage;
    }

    @GetMapping("/login/fail")
    public String fail(){
        return "login/joinFail";
    }

    @GetMapping("/login/success")
    public String success(){
        return "login/joinSuccess";
    }


    @GetMapping("/login/login")
    public String login(){
        return "login/login";
    }

//    @PostMapping("/login/loginProcess")
//    public String loginProcess(LoginVO lvo, HttpSession session, RedirectAttributes redirectAttributes){
//        String returnPage = "";
//
//        try {
//            session.getAttribute("UID");
//            LoginVO login = lsrv.login(lvo); //사용자가 입력한 아이디 비번을 가지고 가서 아이디와 일치하는 로우값을 가져온다.
//
//            if (login == null){
//                returnPage = "redirect:/login/login";
//                return returnPage;}
//
//            boolean passwordMatch = passwordEncoder.matches(lvo.getPassword(), login.getPassword());
//
//            if (passwordMatch == true) {
//                session.setAttribute("UID", login);
//                returnPage = "redirect:/list";
//                System.out.println("login ok");
//            } else {
//                session.setAttribute("UID", null);
//                redirectAttributes.addFlashAttribute("msg", false);
//                returnPage = "redirect:/login/login";
//                System.out.println("login fail");
//            }
//        } catch (Exception e){
//            session.setAttribute("UID", null);
//            redirectAttributes.addFlashAttribute("msg", false);
//            returnPage = "redirect:/login/login";
//            System.out.println("login fail");
//        }
//        finally {
//            return returnPage;
//        }
//    }


    @GetMapping("/login/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("UID");
        return "redirect:/list";
    }

    @GetMapping("/login/memberInfo")
    public ModelAndView memberInfo (ModelAndView mv, HttpSession session){
        mv.setViewName("/login/memberInfo");
        LoginVO lvo = (LoginVO) session.getAttribute("UID");
        mv.addObject("data", lsrv.readOneMember(lvo));
        return mv;
    }

    @PostMapping("login/changeNick")
    public ModelAndView changeNick(ModelAndView mv, LoginVO lvo){
        mv.setViewName("/login/changeNick");
        mv.addObject("data",lvo);
        return mv;
    }

    @ResponseBody
    @PostMapping("/passwordCheck")
    public void passwordCheck(String userid, String password, HttpServletResponse res){
        String encodedPassword = lsrv.checkEncodedPassword(userid);
        boolean passwordMatch = passwordEncoder.matches(password, encodedPassword);
        res.setContentType("application/json; charset=UTF-8");
        try {
            res.getWriter().print(passwordMatch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/changeNickOk")
    public String changeNickOk(LoginVO lvo, HttpSession session){
        int cnt = lsrv.changeNick(lvo);
        session.removeAttribute("UID");
        session.setAttribute("UID",lvo);
        return "redirect:/login/memberInfo";
    }

    @PostMapping("/login/changePassword")
    public ModelAndView changePassword(ModelAndView mv, LoginVO lvo){
        mv.setViewName("/login/changePassword");
        mv.addObject("data",lvo);
        return mv;
    }

    @PostMapping("/changePasswordOk")
    public String changePasswordOk(LoginVO lvo){
        String inputPassword = lvo.getPassword();
        String encodedPassword = passwordEncoder.encode(inputPassword);
        lvo.setPassword(encodedPassword);
        lvo.setPasswordcheck(encodedPassword);
        int ctn = lsrv.updatePassword(lvo);

        return "redirect:/login/memberInfo";
    }

}
