package fi.oispakaljaa.karhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    @RequestMapping("*")
    public String handleDefault() {
        return "index";
    }

    @RequestMapping("/addbar")
    public String addBarPage() {
        return "addbar";
    }

    @RequestMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @RequestMapping("/signin")
    public String signin() {
        return "signin";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

}
