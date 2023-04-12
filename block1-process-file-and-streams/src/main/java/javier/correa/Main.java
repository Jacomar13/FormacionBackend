package javier.correa;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Main {
    private static ArrayList<Person> personas = new ArrayList<Person>();
    public static void main(String[] args) throws IOException, InvalidLineFormatException {
        lecturaDeArchivos();
    }
    public static void lecturaDeArchivos()
            throws IOException, InvalidLineFormatException {
        
        //Obtener la ruta del archivo
        Path path = Paths.get("src/test/personas.csv");
        //Leer el archivo
        try(BufferedReader reader = Files.newBufferedReader(path)){
            //Leerlo por línea
            String texto = reader.readLine();
            //Mientras que el texto no sea nulo, que siga leyendo
            while (texto != null) {
                //Partimos el texto por :
                String[] partes = texto.split(":");

                Person p = new Person();

                //Mandamos un mensaje en el caso de que el nombre sea nulo
                if (partes[0].equals("")) {
                    throw new InvalidLineFormatException(texto + "    -> El nombre es obligatorio. Hay 3 espacios en el campo y esto se considera como blank.");
                }
                if ( contadorDeDosPuntos(texto, ':') == 1){
                    throw new InvalidLineFormatException(texto + "  -> Falta el último delimitador de campo (:)");
                }
                if ( contadorDeDosPuntos(texto, ':') == 0){
                    throw new InvalidLineFormatException(texto + "  -> Faltan dos delimitadores de campo");
                }
                if (partes.length >=1 ) {p.nombre = partes[0];}
                if (partes.length >=2 && !partes[1].equals("")) {p.pueblo = partes[1];}
                else {p.pueblo = "desconocido";}
                if (partes.length >=3) {p.edad = Integer.parseInt(partes[2]);}
                else {p.edad = 0;}
                System.out.println(p);

                personas.add(p);
                texto = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filtroPorEdad();
        filtroPorLetraA();
        filtroPorEdadYCiudadMadrid();
        filtroPorEdadYCiudadBarcelona();
    }


    //Método para calcular el número de veces que se repite un carácter en un String
    public static int contadorDeDosPuntos(String texto, char caracter) {
        int posicion, contador = 0;
        //Se busca la primera vez que aparece
        posicion = texto.indexOf(caracter);
        //Mientras se encuentre el caracter
        while (posicion != -1) {
            //se cuenta
            contador++;
            //Se sigue buscando a partir de la posición siguiente a la encontrada
            posicion = texto.indexOf(caracter, posicion + 1);
        }
        return contador;
    }

    public static void filtroPorEdad() {
        System.out.println();
        System.out.println("////Filtro por edades");
        personas.stream()
                .filter(persona -> persona.getEdad() < 25 && persona.getEdad() > 0)
                .map(Person::toString)
                .forEach(System.out::println);
    }
    public static void filtroPorLetraA(){
        System.out.println();
        System.out.println("////Personas que no empiezan por la A");
        personas.stream()
                .filter(persona -> persona.getNombre().charAt(0) != 'A' && persona.getNombre().charAt(0) != 'Á')
                .map(Person::toString)
                .forEach(System.out::println);
    }

    public static void filtroPorEdadYCiudadMadrid() {
        System.out.println();
        System.out.println("////Filtro por edades y que sean de Madrid");
        System.out.println(personas.stream()
                .filter(persona -> persona.getEdad() < 25 && persona.getEdad() > 0 && persona.getPueblo().equals("Madrid"))
                .findAny().get());
    }
    public static void filtroPorEdadYCiudadBarcelona() {
        System.out.println();
        System.out.println("////Filtro por edades y que sean de Barcelona");
        System.out.println(personas.stream()
                .filter(persona -> persona.getEdad() < 25 && persona.getEdad() > 0 && persona.getPueblo().equals("Barcelona"))
                .findAny().get());
    }
}