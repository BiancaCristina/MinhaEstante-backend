package br.ufu.facom.bianca.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.ufu.facom.bianca.services.DBService;


@Configuration
@Profile("test") // Identifica o profile test
public class TestConfig {
	// Essa classe guarda as configs de teste
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instanciateDataBase() throws ParseException {
		dbService.instantiateTestDataBase();
		
		return true; // 
	}
}
