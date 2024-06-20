package com.example.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("loginUsername");
        if (username != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("name", username);
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "home"; // 굳이 웹 페이지로 리턴받을 필요없음 String 값을 ㅗ반한
    }

}
