package br.ufu.facom.bianca;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufu.facom.bianca.domain.Autor;
import br.ufu.facom.bianca.domain.Categoria;
import br.ufu.facom.bianca.domain.Editora;
import br.ufu.facom.bianca.domain.Leitor;
import br.ufu.facom.bianca.domain.Livro;
import br.ufu.facom.bianca.repositories.AutorRepository;
import br.ufu.facom.bianca.repositories.CategoriaRepository;
import br.ufu.facom.bianca.repositories.EditoraRepository;
import br.ufu.facom.bianca.repositories.LeitorRepository;
import br.ufu.facom.bianca.repositories.LivroRepository;

@SpringBootApplication
public class MinhaEstanteBackendApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private EditoraRepository editoraRepository; 
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LeitorRepository leitorRepository; 
	
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
				
		// Adicionando cada livro na editora
		ed1.getLivrosPublicados().addAll(Arrays.asList(l1,l2,l3));
		ed2.getLivrosPublicados().addAll(Arrays.asList(l4,l5,l6));
		ed3.getLivrosPublicados().addAll(Arrays.asList(l7,l8));
		
		editoraRepository.saveAll(Arrays.asList(ed1,ed2,ed3));
		
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
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5));
		livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6,l7,l8));
		
		// Autores
		Autor a1 = new Autor(null, "John Green");
		Autor a2 = new Autor(null, "Dan Brown");
		Autor a3 = new Autor(null, "Bernard Cornwell");
		Autor a4 = new Autor(null, "Fulano");
		Autor a5 = new Autor(null, "Ciclano");
					
		// Adicionando autores aos livros
		l1.getAutores().add(a1);
		l2.getAutores().addAll(Arrays.asList(a4,a5));
		l3.getAutores().add(a4);
		l4.getAutores().addAll(Arrays.asList(a4,a5));
		l5.getAutores().addAll(Arrays.asList(a4,a5));
		l6.getAutores().add(a2);
		l7.getAutores().add(a3);
		l8.getAutores().add(a5);
		
		// Leitores
		Leitor le1 = new Leitor(null, "Bianca Cristina", "bianca@gmail.com","123");
		Leitor le2 = new Leitor(null, "Thaynara Silva", "thaynara@gmail.com","123");
		Leitor le3 = new Leitor(null, "Gustavo Fernandes","gustavo@gmail.com","123");
		
		leitorRepository.saveAll(Arrays.asList(le1,le2,le3));
		
		le1.getLivrosLidos().addAll(Arrays.asList(l1,l2,l3,l4));
		le2.getLivrosLidos().addAll(Arrays.asList(l4,l8));
		le3.getLivrosDesejados().addAll(Arrays.asList(l5,l6,l7));
		
		l1.getLeitoresLeram().add(le1);
		l2.getLeitoresLeram().add(le1);
		l3.getLeitoresLeram().add(le1);
		l4.getLeitoresLeram().addAll(Arrays.asList(le1,le2));
		l8.getLeitoresLeram().add(le2);
		
		l5.getLeitoresDesejam().add(le3);
		l6.getLeitoresDesejam().add(le3);
		l7.getLeitoresDesejam().add(le3);
		
		le1.getAutoresFavoritos().addAll(Arrays.asList(a1,a2));
		le2.getAutoresFavoritos().addAll(Arrays.asList(a2));
		
		a1.getLeitoresGostam().add(le1);
		a2.getLeitoresGostam().addAll(Arrays.asList(le1,le2));
		
		le2.getEditorasFavoritas().addAll(Arrays.asList(ed1,ed2,ed3));
		le3.getEditorasFavoritas().add(ed3);
		
		ed1.getLeitoresFavoritam().add(le2);
		ed2.getLeitoresFavoritam().add(le2);
		ed3.getLeitoresFavoritam().addAll(Arrays.asList(le2,le3));
		
		autorRepository.saveAll(Arrays.asList(a1,a2,a3,a4,a5));
		livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6,l7,l8));
		editoraRepository.saveAll(Arrays.asList(ed1,ed2,ed3));
		
	}
}
