package javier.correa.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Block5PropertiesApplication implements CommandLineRunner {

	@Value("${greeting}")
	private String greeting;
	@Value("${saludo}")
	private String saludo;

	@Value("${my.number}")
	private Integer numero;

	@Value("${new.property}")
	private String hola;
	public static void main(String[] args) {
		SpringApplication.run(Block5PropertiesApplication.class, args );
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello world!");
		System.out.println("El valor de greeting es: " + greeting);
		System.out.println("El valor de my.number es: " + numero);
		System.out.println("El valor de new.property es: " + hola);
		System.out.println(saludo);
	}
}
