package com.example.autoPujcovna.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "autoPujcovna")
public class MainPage {
    
    @GetMapping()
    public String getMethodName() {
    
        return "mainPage";
    }
    

}
