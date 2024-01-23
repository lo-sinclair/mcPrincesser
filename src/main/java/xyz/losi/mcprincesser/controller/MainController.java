package xyz.losi.mcprincesser.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.losi.mcprincesser.domain.User;

import java.util.Map;

@Controller
public class MainController {
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user, Map<String, Object> model) {
        model.put("some", "Hello, letsCode!");
        model.put("user", user);
        return "main";
    }

}
