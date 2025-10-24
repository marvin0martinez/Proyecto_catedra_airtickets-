// src/main/java/sv/edu/udb/airtickets/config/StaticResourcesConfig.java
package sv.edu.udb.airtickets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Solo mapeos válidos con PathPatternParser (sin /**/*.ext)
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/assets/");

        registry.addResourceHandler("/login.html", "/index.html")
                .addResourceLocations("classpath:/static/");
    }
}
