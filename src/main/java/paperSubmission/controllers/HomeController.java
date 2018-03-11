package paperSubmission.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import paperSubmission.model.template.PublicationTemplate;
import paperSubmission.model.template.UserTemplate;
import paperSubmission.services.PublicationServices;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private static PublicationServices publicationServices;

    @GetMapping("/home")
    String getHome(Model model,HttpSession session) {
        int user_id = ((UserTemplate)session.getAttribute("user")).getId();
        System.out.println("User id is " + user_id);
        model.addAttribute("userName",session.getAttribute("userLogin"));
        model.addAttribute("publications",publicationServices.getPublicationsOfAuthor(user_id));
        return "home";
    }

    @PostMapping("/home")
    String postHome(HttpSession session, @RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("link") String link ) {

        int user_id = ((UserTemplate)session.getAttribute("user")).getId();
        PublicationTemplate publicationTemplate = new PublicationTemplate();
        publicationTemplate.setTitle(title);
        publicationTemplate.setDescription(description);
        publicationTemplate.setLink(link);
        publicationTemplate.setAuthorId(user_id);
        publicationServices.addNewPublication(publicationTemplate);
        return "home";
    }
    @GetMapping("/publications")
    String getPublicationsList(Model model){
        model.addAttribute("publications",publicationServices.getAllPublications());
        return "publications";
    }

}
