package org.example.givingpriveliges.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String customLogin() {
        System.out.println("Called");
        return "customlogin"; // Return the name of your Thymeleaf template
    }
}
