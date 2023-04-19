package javier.correa.block6personcontrollers.controladores;

import javier.correa.block6personcontrollers.objetos.Ciudad;
import javier.correa.block6personcontrollers.objetos.Persona;
import javier.correa.block6personcontrollers.servicios.CiudadService;
import javier.correa.block6personcontrollers.servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping("/controlador2")

public class Controlador2 {

    @Autowired
    PersonaService personaService;
    @Autowired
    CiudadService ciudadService;

    @GetMapping("/getPersona")
    public Persona mostrarPersona(){
        return personaService.getPersona();
    }

    @GetMapping("/getCiudades")
    public ArrayList<Ciudad> mostrarCiudades(){
        ArrayList<Ciudad> ciudades = ciudadService.getCiudades();
        return ciudades;
    }
}
