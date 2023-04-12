package javier.correa.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ClasePrimaria {

    @PostConstruct
    public void ClasePrimaria() {
        System.out.println("Hola desde clase primaria");
    }
}
