package dio.spring.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Bem-vindo à minha Spring Boot Web API";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('USER', 'MANAGER')")  // user e admin podem acessar
    public String users() {
        return "Usuário Autorizado";
    }

    @GetMapping("/managers")
    @PreAuthorize("hasRole('MANAGER')")  // somente admin pode acessar
    public String managers() {
        return "Admin Autorizado";
    }

    // Endpoint para debug - mostrar usuário e roles atuais
    @GetMapping("/whoami")
    public String whoAmI(Authentication authentication) {
        return "Usuário: " + authentication.getName() + ", Roles: " + authentication.getAuthorities();
    }
}
