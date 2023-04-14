package javier.correa.block5profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;


@Configuration
@Profile("INTEGRATION")
public class INTEGRATION {
    @Value("${spring.profiles.active}")
    private String perfilActivo;

    @Autowired
    @Value("${bd.url}")
    private String enlace;
    @Bean
    CommandLineRunner ejecutarIntegracion(){
        return args -> {
            System.out.println("Hola desde el perfil de " + perfilActivo);
            System.out.println("EL enlace a la base de datos es la siguiente: " + enlace);
        };
    }

}
