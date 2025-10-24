package sv.edu.udb.airtickets.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaRedirectController {

    // Mostrar primero el login
    @GetMapping({"/", "/login"})
    public String login(HttpServletRequest req) {
        return "redirect:" + req.getContextPath() + "/login.html";
    }

    // Rutas de la SPA -> index
    @GetMapping({"/vuelos", "/reservas", "/aerolineas", "/estadisticas", "/reclamos"})
    public String appRoutes() {
        return "forward:/index.html";
    }
}
