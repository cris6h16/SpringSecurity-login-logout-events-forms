package org.cris6h16.example.AuthListener;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthEventListener {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        // ...

        boolean isAdmin = success
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            //... do something
            // for example
            // - save the event for audit
            // - send some email to someone
            // - send a message to telegram audit group by a bot
            // - etc
        }

    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        // ...
    }
}

