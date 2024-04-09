package org.cris6h16.example.Security;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*
        I was explained all the below config in my previous repos..
         */
        return http
                .formLogin(formLogin ->
                                formLogin
                                        .loginPage("/login") // for redirection
//                                        .failureForwardUrl("/login?error") // if failed do a customized message using a query param
                                        .successForwardUrl("/auth/info") // if success get to see an Auth Path
                                        .usernameParameter("password") // parameter which reoresent `name` in the form
                                        .passwordParameter("username") // parameter which reoresent `password` in the form
//                                        .loginProcessingUrl("/login-processor") // submit the form to this URL
                        //...
                )
                .logout(logout ->
                                logout
                                        .logoutUrl("/logout") // logout URL
                                        .logoutSuccessUrl("/") // redirect to this URL after logout
                                        .invalidateHttpSession(true) // HTTP session is used to identify the user in the app
                                        .deleteCookies("my-custom-cookie") // delete cookies
                                        .clearAuthentication(true) // clear the authentication (Obj which holds the user's credentials and more...)
                                        .addLogoutHandler(new CookieClearingLogoutHandler("my-custom-cookie2")) // same as deleteCookies but you can separate them
                        //...
                )
                .sessionManagement(sessionManagement ->
                                sessionManagement
                                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession) // I explained this in one og my firsts repos
                                        .maximumSessions(1) // only one session per user
                                        .maxSessionsPreventsLogin(false) // if the user is already logged in, then prevent the user from logging in again, false invalidates the previous session
                                        .expiredUrl("/login?expired")  // if the session is expired, then redirect to this URL
//                              .sessionRegistry(sessionRegistry()) // session registry
                        //...
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .build();
    }

    /* InMemoryUserDetailsManager is a simple in-memory implementation of UserDetailsService
     and creates users easily.. for testing purposes

     you implemented it, then Spring Security will use it to authenticate users, also
     Spring Security won't generate a default login credential ( user, password-in-logs )
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder() // you can do your own impl for add more attributes
                .username("cris6h16")
                .password("cris6h16") // password is stored encrypted
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);// works like a password storage
    }

    // implementations of UserDetailsService Example non-in-memory
    /*
    @Service
    public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0])) // Assuming roles are stored as a collection in User entity
                .build();
    }
}


     */


    /* Handling:

    - BadCreadentialsException
    - UsernameNotFoundException
    - AccountExpiredException
    - ProviderNotFoundException
    - DisabledException
    - LockedException
    - AuthenticationServiceException
    - CredentialsExpiredException
    - InvalidBearerTokenException
     */
    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

//    @Bean
//    public SessionRegistry sessionRegistry() {
//        return new SessionRegistryImpl();
//    }
}
