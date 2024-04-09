package org.cris6h16.example.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String login() {
        return "/login";
    }

    @GetMapping("/logout")
    @PreAuthorize("permitAll()")
    public String logout() {
        return "logoutConfirmation";
    }


    @RequestMapping("/auth/info")
    @PreAuthorize("isAuthenticated()") // redirected if not
    //see DI in Spring if you don't understand this
    public String authInfo(Authentication authentication, Model model) {
        model.addAttribute("auth", authentication);
        return "/auth/info";
    }
}
