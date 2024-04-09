//package org.cris6h16.example.Controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
//@RestController
//@RequestMapping(value = "/api")
//public class ApiController {
//
//    //    @GetMapping("/")
////    @GetMapping("")
//    @RequestMapping(value = {"", "/"}, produces = {"text/html"}, method = {GET})
//    @PostAuthorize("permitAll()")
//    public ResponseEntity<?> apiHome() {
//        return ResponseEntity.status(200).body("Hello from API Home");
//    }
//
//    //---------------- Only are examples.... don't forget that are API examples, it shouldn't have HTML buttons , etc. ----------------\\
//
//    @RequestMapping("/auth-info-way1")
//    @PreAuthorize("isAuthenticated()")
//    //see DI in Spring if you don't understand this
//    public String whoIAm(Authentication authentication) throws JsonProcessingException {
//        return new ObjectMapper().writeValueAsString(authentication); // we should use DTOs
//    }
//
//    @RequestMapping("/auth-info-way2")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ETC')")
//    public String whoIAm() throws JsonProcessingException {
//        // see Spring Security's architecture if you don't understand this
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return new ObjectMapper().writeValueAsString(authentication);// we should use DTOs
//    }
//
//    // there are more ways.....
//}
