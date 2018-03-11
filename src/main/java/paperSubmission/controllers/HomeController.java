package paperSubmission.controllers;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import paperSubmission.model.template.PublicationTemplate;
import paperSubmission.model.template.UserTemplate;
import paperSubmission.services.PublicationServices;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    static {
        PropertyConfigurator.configure(HomeController.class.getClassLoader().getResource("log4j.properties"));
    }
    private static Logger LOGGER = Logger.getLogger(HomeController.class);

    @Autowired
    PublicationServices publicationServices;

    @GetMapping("/home")
    ModelAndView getHome(Model model, HttpSession session) {
        if(filter(session)) {
            LOGGER.debug("showing home page for the user");
            int user_id = (int) session.getAttribute("userId");
            System.out.println("User id is " + user_id);
            System.out.println(publicationServices);
            System.out.println(publicationServices.getPublicationsOfAuthor(user_id));
            model.addAttribute("nickName", session.getAttribute("userLogin"));
            model.addAttribute("publications", publicationServices.getPublicationsOfAuthor(user_id));
            return new ModelAndView("home");
        }else {
            return new ModelAndView("login");
        }

    }

    @PostMapping("/addpub")
    ModelAndView postHome(Model model,HttpSession session, @RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("link") String link ) {
        if(filter(session)) {
            int user_id = (int) session.getAttribute("userId");
            PublicationTemplate publicationTemplate = new PublicationTemplate();
            publicationTemplate.setTitle(title);
            publicationTemplate.setDescription(description);
            publicationTemplate.setLink(link);
            publicationTemplate.setAuthorId(user_id);
            String result = publicationServices.addNewPublication(publicationTemplate);
            model.addAttribute("message",result);
            LOGGER.debug("Result of adding new publication " + result);
            return new ModelAndView("redirect:/home").addObject(model);
        }else {
            return new ModelAndView("login");
        }


    }
    @GetMapping("/publications")
    String getPublicationsList(Model model,HttpSession session){
        if(filter(session)) {
            LOGGER.debug("displaying total list of publications");
            model.addAttribute("publications", publicationServices.getAllPublications());
            return "publications";
        }else {
            return "login";
        }
    }

    @GetMapping("/addpub")
    ModelAndView getAddPub(HttpSession session){
        if(filter(session)) {
            return new ModelAndView("addpub");
        }else {
            return new ModelAndView("login");
        }
    }

    private boolean filter(HttpSession session){
        return  session.getAttribute("userId")!=null;
    }
}
