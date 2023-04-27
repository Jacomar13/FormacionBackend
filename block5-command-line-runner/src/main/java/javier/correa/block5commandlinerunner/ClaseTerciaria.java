package javier.correa.block5commandlinerunner;


import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;


public class ClaseTerciaria implements CommandLineRunner{

    public void ClaseTerciaria(ArrayList<String> mensaje){
        System.out.println("Hola desde la tercera clase");
        for (String arg : mensaje) {
            System.out.println(arg + " ");
        }
        System.out.println();

    }
    @Override
    public void run(String... args) throws Exception {
    }
}
