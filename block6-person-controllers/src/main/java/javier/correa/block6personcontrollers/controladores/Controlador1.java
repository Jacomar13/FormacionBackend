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


    //Definimos una lista de las ciudades para poder posteriormente, mostrarle todas las ciudades creadas posteriormente
    private ArrayList<Ciudad> ciudades= new ArrayList<>();

    //Recibimos una persona por Header con método GET y la imprimimos por terminal para comprobar que se está enviando correctamente

    @GetMapping("/controlador1/addPersona")
    public void recibirPersona (@RequestHeader String nombre, @RequestHeader String poblacion, @RequestHeader int edad){
        p.setNombre(nombre);
        p.setPoblacion(poblacion);
        p.setEdad(edad);
        System.out.println(p);
    }

    //Creamos una función que devuelve la persona creada para poder utilizarla en el controlador2
    public Persona getPersona() {
        return p;
    }

    //Recibimos una ciudad  y la añadimos al array de ciudades, también la mostramos por consola para comprobar que se esté pasando correctamente

    @PostMapping("/controlador1/addCiudad")
    public void addCiudades(@RequestBody Ciudad ciudad){
        ciudades.add(ciudad);
        System.out.println(ciudad);
    }

    //Función que devuelve las ciudades escritas
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
