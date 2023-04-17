package javier.correa.block6personcontrollers.controladores;

import javier.correa.block6personcontrollers.servicios.Ciudad;
import javier.correa.block6personcontrollers.servicios.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controlador1 {
    @Autowired
    Persona p;



    private List<Ciudad> ciudades= new ArrayList<>();

    @GetMapping("/controlador1/addPersona")
    public void recibirPersona (@RequestHeader String nombre, @RequestHeader String poblacion, @RequestHeader int edad){
        p.setNombre(nombre);
        p.setPoblacion(poblacion);
        p.setEdad(edad);
        System.out.println(p);
    }
    public Persona getPersona() {
        return p;
    }

    @PostMapping("/controlador1/addCiudad")
    public void addCiudades(@RequestBody Ciudad ciudad){
        ciudades.add(ciudad);
        System.out.println(ciudad);
    }

    public List<Ciudad> getCiudades(){
        return ciudades;
    }


    @Qualifier("bean1")
    @Bean
    public Persona bean1(){
        Persona persona1 = new Persona("Bean1", "Alicante", 12);
        return persona1;
    }

    @Qualifier("bean2")
    @Bean
    public Persona bean2(){
        Persona persona2 = new Persona("Bean2", "Pamplona", 32);
        return persona2;
    }
    @Qualifier("bean3")
    @Bean
    public Persona bean3(){
        Persona persona3 = new Persona("Bean3", "Roma", 91);
        return persona3;
    }

    @GetMapping("/controlador1/bean/")
    public String errorBean () {
        return "Debes de elegir entre bean1, bean2 y bean3 \n \n Un ejemplo sería poner http://localhost:8080/controlador1/bean/bean1";

    }

    @GetMapping("/controlador1/bean/{bean}")
    public Persona obtenerBeans(@PathVariable String bean){
        switch (bean){
            case "bean1" :
                return bean1();
            case "bean2":
                return bean2();
            case "bean3":
                return bean3();
            default:
                return null;
        }
    }




}
