package javier.correa.block5commandlinerunner;


import org.springframework.boot.CommandLineRunner;



//Usando la libreria lombok

public class ClaseSecundaria implements CommandLineRunner{

    public void ClaseSecundaria(){
        System.out.println("Hola desde clase secundaria");
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
