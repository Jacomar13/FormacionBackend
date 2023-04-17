package javier.correa.block6personcontrollers.servicios;


import org.springframework.stereotype.Service;

@Service
public class Persona {
    private String nombre;
    private String poblacion;
    private int edad;

    public Persona() {
    }

    public Persona(String nombre, String población, int edad) {
        this.nombre = nombre;
        this.poblacion = población;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", población='" + poblacion + '\'' +
                ", edad=" + edad +
                '}';
    }
}
