package dev.kerimfettahoglu.oauthresourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/func1")
    public String func1() {
        return "Hello JWT";
    }
}
