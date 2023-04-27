package javier.correa;

public class Person {
    String nombre;
    String pueblo;
    int edad;

    public Person() {
    }
    //Cambios
    public Person(String nombre, String pueblo, int edad) {
        this.nombre = nombre;
        this.pueblo = pueblo;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPueblo() {
        return pueblo;
    }

    public void setPueblo(String pueblo) {
        this.pueblo = pueblo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nombre='" + nombre + '\'' +
                ", pueblo='" + pueblo + '\'' +
                ", edad=" + edad +
                '}';
    }
}