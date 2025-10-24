// src/main/java/sv/edu/udb/airtickets/config/SecurityConfig.java
package sv.edu.udb.airtickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sv.edu.udb.airtickets.security.JwtAuthFilter;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwt;

    public SecurityConfig(JwtAuthFilter jwt) {
        this.jwt = jwt;
    }

    private static final String[] SWAGGER = {
            "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**"
    };

    private static final String[] PUBLIC_STATIC = {
            "/", "/index.html", "/login.html", "/favicon.ico",
            "/assets/**",
            "/manifest.webmanifest", "/*.png", "/*.svg", "/*.ico",
            "/login", "/vuelos/**", "/reservas/**", "/aerolineas/**", "/estadisticas/**", "/reclamos/**"
    };

    /** BCrypt para validar contraseñas almacenadas con hash */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // fuerza por defecto = 10
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(auth -> auth
                // Swagger
                .requestMatchers(SWAGGER).permitAll()
                // Endpoint público de autenticación
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                // Archivos estáticos y rutas de la SPA (login/index)
                .requestMatchers(HttpMethod.GET, PUBLIC_STATIC).permitAll()
                // Zona admin
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                // Resto de la API protegida
                .requestMatchers("/api/**").authenticated()
                // Cualquier otra cosa (por ejemplo, estáticos no listados)
                .anyRequest().permitAll()
        );

        // Filtro JWT antes del UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /** CORS abierto para localhost durante desarrollo */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration c = new CorsConfiguration();
        c.setAllowCredentials(true);
        c.setAllowedOriginPatterns(List.of("http://localhost:*", "http://127.0.0.1:*"));
        c.setAllowedHeaders(List.of("*"));
        c.setExposedHeaders(List.of("Authorization"));
        c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
        source.registerCorsConfiguration("/**", c);
        return new CorsFilter(source);
    }
}
