package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {
    @RequestMapping(value = { "/course"}, method = RequestMethod.GET)
    public String viewCourse(){
        return "index";
    }
}
