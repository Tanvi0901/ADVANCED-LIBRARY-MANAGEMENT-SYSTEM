package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pingController {
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping(){
        return "CMS Service Say Hello..";
    }
}
