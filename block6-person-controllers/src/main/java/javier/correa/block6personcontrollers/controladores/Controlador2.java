package javier.correa.block6personcontrollers.controladores;

import javier.correa.block6personcontrollers.servicios.Ciudad;
import javier.correa.block6personcontrollers.servicios.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Controlador2 {

    //Utilizando la anotación de Auto
    @Autowired
    Controlador1 controlador1;

    @GetMapping("/controlador2/getPersona")
    public Persona devolverPersona(){
        Persona p = controlador1.getPersona();
        p.setEdad(p.getEdad()* 2);
        return p;
    }

    @GetMapping("/controlador2/getCiudades")
    public List<Ciudad> devolverCiudadess() {
        return controlador1.getCiudades();
    }

}
