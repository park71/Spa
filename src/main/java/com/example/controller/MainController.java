package com.example.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public Map<String, Object> homePage(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        String username = (String) session.getAttribute("loginUsername");
        if (username != null) {
            response.put("loggedIn", true);
            response.put("name", username);
        } else {
            response.put("loggedIn", false);
        }
        return response; // JSON 형식으로 반환
    }
}
