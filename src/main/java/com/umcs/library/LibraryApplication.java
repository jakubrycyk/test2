package com.umcs.library;

import com.umcs.library.repository.person.impl.PersonJDBCTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class LibraryApplication implements CommandLineRunner{

	PersonJDBCTemplateRepository personJDBCTemplateRepository;

	@Autowired
	public LibraryApplication(PersonJDBCTemplateRepository personJDBCTemplateRepository) {
		this.personJDBCTemplateRepository = personJDBCTemplateRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println(personJDBCTemplateRepository.findAll());
		//Tworzenie użytkoników
		//Dodanie zioru książek
		//Użytkownik a wypożycza książkę
		//Pokazanie statusów
		//Użytkownik b wypożycza książkę
		//Pokazanie statusów
		//Użytkownik a gubi książkę
		// statusy
		// Użytkownik b oddaje książkę
		// statusy
	}
}
