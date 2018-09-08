package br.ufu.facom.bianca;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufu.facom.bianca.domain.Categoria;
import br.ufu.facom.bianca.repositories.CategoriaRepository;

@SpringBootApplication
public class MinhaEstanteBackendApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MinhaEstanteBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Romance");
		Categoria cat2 = new Categoria(null,"Ficção Científica");
		Categoria cat3 = new Categoria(null,"Medieval");
		Categoria cat4 = new Categoria(null,"Policial");
		Categoria cat5 = new Categoria(null,"Suspense");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5));
		
	}
}
