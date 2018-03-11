package paperSubmission.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import paperSubmission.model.template.UserTemplate;
import paperSubmission.services.UserServices;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {
    @Autowired
    UserServices userServices;

    @GetMapping("/login")
    String getLogin(){
        return "login";
    }
    @PostMapping("/login")
    ModelAndView postLogin(@RequestParam("nickName") String nickName, @RequestParam("password") String password, HttpSession session, Model model){

        UserTemplate userTemplate = userServices.auth(nickName,password);

        if (userTemplate!=null)
        {

            session.setAttribute("userLogin",userTemplate.getNickName());
            session.setAttribute("userId",userTemplate.getId());
            return new ModelAndView("redirect:/home");
        } else {
            model.addAttribute("errMessage","Failed to login");
            return new ModelAndView("login");
        }
    }

    @GetMapping("/register")
    String getRegister(){
        return "register";
    }

    @PostMapping("/register")
    String postRegister(@RequestParam("nickName") String nickName,
                        @RequestParam("email") String email,
                        @RequestParam("password") String pass,
                        @RequestParam("name") String name,Model model){

        UserTemplate user = new UserTemplate(name,email,nickName,pass);

        String result =userServices.register(user);
        if (result.equals("SUCCESS"))
        {
            return "login";
        } else
        {
            model.addAttribute("errMessage",result);
            return "register";
        }
    }

    @GetMapping("/logout")
    String getLogout(HttpSession session,Model model){
        if(session!=null)
        {
            session.invalidate();
            model.addAttribute("errMessage", "You have logged out successfully");
        }
        return "login";
    }


}
