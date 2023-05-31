package javier.correa.block13mongodb;

import javier.correa.block13mongodb.application.PersonaService;
import javier.correa.block13mongodb.domain.Persona;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;


@SpringBootApplication
public class Block13MongodbApplication{

	public static void main(String[] args) {
		SpringApplication.run(Block13MongodbApplication.class, args);
	}

		/*private static final Logger LOG = LoggerFactory.getLogger("AppsDeveloperBlog");
		private final PersonaService personaService;
		@Autowired
		public Block13MongodbApplication(PersonaService personaService) {
			this.personaService = personaService;
		}

		@Override
		public void run(String... args) {
			personaService.savePerson(new Persona(
					"Shubham", Arrays.asList("Harry potter", "Waking Up"), new Date(769372200000L)));
			personaService.savePerson(new Persona(
					"Sergey", Arrays.asList("Startup Guides", "Java"), new Date(664309800000L)));
			personaService.savePerson(new Persona(
					"David", Arrays.asList("Harry potter", "Success"), new Date(695845800000L)));
			personaService.savePerson(new Persona(
					"Ivan", Arrays.asList("Secrets of Butene", "Meeting Success"), new Date(569615400000L)));
			personaService.savePerson(new Persona(
					"Sergey", Arrays.asList("Harry potter", "Startup Guides"), new Date(348777000000L)));
			LOG.info("Getting all data from MongoDB: \n{}",
					personaService.getAllPerson());
			LOG.info("Getting paginated data from MongoDB: \n{}",
					personaService.getAllPersonPaginated(0, 2));
			LOG.info("Getting person By name 'Sergey': {}",
					personaService.findByName("Sergey"));
			LOG.info("Getting all person By name 'Sergey': {}",
					personaService.findOneByName("Sergey"));
			LOG.info("Getting people between age 22 & 26: {}",
					personaService.findByAgeRange(22, 26));
		}*/
}
