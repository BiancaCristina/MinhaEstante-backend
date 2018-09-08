package br.ufu.facom.bianca;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufu.facom.bianca.domain.Categoria;
import br.ufu.facom.bianca.domain.Editora;
import br.ufu.facom.bianca.domain.Livro;
import br.ufu.facom.bianca.repositories.CategoriaRepository;
import br.ufu.facom.bianca.repositories.EditoraRepository;
import br.ufu.facom.bianca.repositories.LivroRepository;

@SpringBootApplication
public class MinhaEstanteBackendApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private EditoraRepository editoraRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(MinhaEstanteBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Categorias
		Categoria cat1 = new Categoria(null,"Romance");
		Categoria cat2 = new Categoria(null,"Ficção Científica");
		Categoria cat3 = new Categoria(null,"Medieval");
		Categoria cat4 = new Categoria(null,"Policial");
		Categoria cat5 = new Categoria(null,"Suspense");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5));
		
		// Editoras
		Editora ed1 = new Editora(null, "Editora Viver");
		Editora ed2 = new Editora(null, "Editora Felicidade");
		Editora ed3 = new Editora(null, "Editora Pessoas");
		
		editoraRepository.saveAll(Arrays.asList(ed1,ed2,ed3));
		
		// Livros
		Livro l1 = new Livro(null, "Quem é você, Alaska?", "Romance clichê", 300, ed1);
		Livro l2 = new Livro(null, "Game of Thrones", "Bastante guerra", 800, ed1);
		Livro l3 = new Livro(null, "Diário de uma paixão", "Outro romance clichê", 250, ed1);
		Livro l4 = new Livro(null, "Detalhe final", "Policial", 400, ed2);
		Livro l5 = new Livro(null, "Neuromancer", "Originou MATRIX", 600, ed2);
		Livro l6 = new Livro(null, "O código da Vinci", "Muito bom", 500, ed2);
		Livro l7 = new Livro(null, "Crônicas Saxônicas", "Quem é Game of Thrones perto dessa saga?", 623, ed3);
		Livro l8 = new Livro(null, "O inocente", "Policial e suspense também", 321, ed3);
		
		livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6,l7,l8));
		
		// Adicionando cada livro na editora
		ed1.getLivrosPublicados().addAll(Arrays.asList(l1,l2,l3));
		ed2.getLivrosPublicados().addAll(Arrays.asList(l4,l5,l6));
		ed3.getLivrosPublicados().addAll(Arrays.asList(l7,l8));
		
		// Adicionando os livros em cada categoria
		cat1.getLivros().addAll(Arrays.asList(l1,l3));
		cat2.getLivros().addAll(Arrays.asList(l5,l6));
		cat3.getLivros().addAll(Arrays.asList(l2,l7));
		cat4.getLivros().addAll(Arrays.asList(l4,l8));
		cat5.getLivros().addAll(Arrays.asList(l8));
		
		// Adicionando as categorias nos livros
		l1.getCategorias().add(cat1);
		l2.getCategorias().add(cat3);
		l3.getCategorias().add(cat1);
		l4.getCategorias().add(cat4);
		l5.getCategorias().add(cat2);
		l6.getCategorias().add(cat2);
		l7.getCategorias().add(cat3);
		l8.getCategorias().addAll(Arrays.asList(cat4,cat5));
		
		// Salvando no repositorio
		

		
	}
}
