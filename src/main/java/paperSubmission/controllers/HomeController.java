package paperSubmission.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import paperSubmission.model.template.Car;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    String getHome(Model model) {
        model.addAttribute("car", new Car(1, "WAZ", 15));
        return "home";
    }

    @PostMapping("/home")
    ModelAndView postHome(@ModelAttribute Car car) {
        ModelAndView home = new ModelAndView("home");
        car.setAmount(car.getAmount() + 10);
        home.addObject("car", car);
        return home;
    }
}
