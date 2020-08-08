package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
@RequiredArgsConstructor
public class HiderController {
    private final UserService userService;

    @ModelAttribute("username")
    public String username(@AuthenticationPrincipal Principal principal) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        return username;
    }
}
