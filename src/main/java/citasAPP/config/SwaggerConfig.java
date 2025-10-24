package citasAPP.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI citasAppOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Citas Médicas")
                        .description("Documentación de la API del sistema CitasAPP desarrollada en Spring Boot")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Cristian Camilo López Grajales")
                                .email("contacto@citasapp.com")
                                .url("https://github.com/camilahernandez2012")));
    }
}
