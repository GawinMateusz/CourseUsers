package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TechnologyController {

    @RequestMapping(value = {"/technology"}, method = RequestMethod.GET)
    public String viewTechnology(){
        return "index";
    }
}
