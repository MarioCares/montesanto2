package dev.mariocares.montesanto2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BibliaController {

    @GetMapping("/Biblia")
    public ModelAndView index(){
        return new ModelAndView("biblia/index");
    }
}
