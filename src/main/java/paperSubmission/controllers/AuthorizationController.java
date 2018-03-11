package paperSubmission.controllers;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import paperSubmission.model.DAO.PublicationsDAOImpl;
import paperSubmission.model.template.UserTemplate;
import paperSubmission.services.UserServices;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {

    static {
        PropertyConfigurator.configure(AuthorizationController.class.getClassLoader().getResource("log4j.properties"));
    }
    private static Logger LOGGER = Logger.getLogger(AuthorizationController.class);

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
            LOGGER.debug("user logged in");
            session.setAttribute("userLogin",userTemplate.getNickName());
            session.setAttribute("userId",userTemplate.getId());
            return new ModelAndView("redirect:/home");
        } else {
            LOGGER.debug("couldn't log in");
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
            LOGGER.debug("registration successful, redirecting to login page");
            return "login";
        } else
        {
            LOGGER.debug("registration failed");
            model.addAttribute("errMessage",result);
            return "register";
        }
    }

    @GetMapping("/logout")
    String getLogout(HttpSession session,Model model){
        if(session!=null)
        {
            LOGGER.debug("user logged out");
            session.invalidate();
            model.addAttribute("errMessage", "You have logged out successfully");
        }
        return "login";
    }


}
