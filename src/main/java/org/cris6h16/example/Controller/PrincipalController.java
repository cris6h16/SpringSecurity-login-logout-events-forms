package org.cris6h16.example.Controller;//package org.cris6h16.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class PrincipalController {


    @RequestMapping(value = {"", "/"}, produces = {"text/html"}, method = {GET})
    @PostAuthorize("permitAll()")
    public String apiHome() {
        return "/home";
    }


}
