package javier.correa.block5commandlinerunner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


@SpringBootApplication
public class Block5CommandLineRunnerApplication implements CommandLineRunner {

	//protected static ArrayList<String> mensaje = new ArrayList<String>();

	public static void main(String[] args) {
		SpringApplication.run(Block5CommandLineRunnerApplication.class, args);
	}

	private ClaseSecundaria claseSecundaria = new ClaseSecundaria();

	private ClaseTerciaria claseTerciaria = new ClaseTerciaria();


	@Override
	public void run(String... args) throws Exception {
		claseSecundaria.ClaseSecundaria();

		ArrayList<String> mensaje = new ArrayList<String>();
		mensaje.add("Hola");
		mensaje.add("que");
		mensaje.add("tal");
		claseTerciaria.ClaseTerciaria(mensaje);
	}

}