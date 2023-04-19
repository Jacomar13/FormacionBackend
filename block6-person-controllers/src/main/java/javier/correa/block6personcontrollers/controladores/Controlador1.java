package javier.correa.block6personcontrollers.controladores;


import javier.correa.block6personcontrollers.objetos.Ciudad;
import javier.correa.block6personcontrollers.objetos.Persona;
import javier.correa.block6personcontrollers.servicios.CiudadService;
import javier.correa.block6personcontrollers.servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")

public class Controlador1 {

    @Autowired
    PersonaService personaService;

    @Autowired
    CiudadService ciudadService;

    @GetMapping("/addPersona")
    public void addPersona(@RequestHeader String nombre, @RequestHeader String poblacion, @RequestHeader int edad){
        Persona persona = personaService.crearPersona(nombre,poblacion,edad);
        persona.setEdad(persona.getEdad()*2);
        System.out.println(personaService);
    }

    @PostMapping("/addCiudad")
    public void addCiudad(@RequestBody Ciudad ciudad){
        ciudadService.crearCiudad(ciudad);
        System.out.println(ciudad);
    }

}
