package javier.correa.block6personcontrollers.servicios;


import javier.correa.block6personcontrollers.objetos.Persona;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private Persona persona;

    public Persona crearPersona(String nombre, String poblacion, int edad) {
        persona = new Persona(nombre, poblacion, edad);
        return  persona;
    }

    public Persona getPersona(){
        return persona;
    }

    @Override
    public String toString() {
        return "PersonaService{" +
                persona +
                '}';
    }
}
