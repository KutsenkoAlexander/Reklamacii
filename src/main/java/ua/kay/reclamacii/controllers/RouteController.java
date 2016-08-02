package ua.kay.reclamacii.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
    @RequestMapping(value = "/{[path:[^\\.]*}")
    public String redirectIndex() {
        return "forward:/";
    }
}
