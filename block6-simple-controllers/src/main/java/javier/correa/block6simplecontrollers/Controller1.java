package javier.correa.block6simplecontrollers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller1 {



    @GetMapping("/user/{nombre}") //Con @GetMapping somos capaces de extraer a partir de una URI con el método GET, un parametro que se nos pasa entre {}
    public String nombre (@PathVariable String nombre){ //Con @PathVariable cogemos el parámetro y lo podemos utilizar, en caso de que recibamos varios, tendremos que ponerlos en el orden corespondiente
        return "Hola " + nombre;
    }

    @PostMapping("/useradd") //Con PostMapping, mediante el método POST, podemos obtener unos parámetros de una URI
    public Persona creacionPersona (@RequestBody Persona p) { //Con @RequestBody le pedimos un cuerpo, que en este caso será a través de un fichero .JSON, a la petición POST

        p.setEdad(p.getEdad() + 1);
        return p;
    }
}
