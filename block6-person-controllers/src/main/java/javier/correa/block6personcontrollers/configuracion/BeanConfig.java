package javier.correa.block6personcontrollers.configuracion;

import javier.correa.block6personcontrollers.objetos.Persona;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    @Qualifier("bean1")
    public Persona persona1(){
        return new Persona("Felipe", "Sevilla", 14);
    }
    @Bean
    @Qualifier("bean2")
    public Persona persona2(){
        return new Persona("Rodrigo", "Jaén", 27);
    }
    @Bean
    @Qualifier("bean3")
    public Persona persona3(){
        return new Persona("María", "Málaga", 20);
    }

}
