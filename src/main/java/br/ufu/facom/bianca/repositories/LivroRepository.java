package br.ufu.facom.bianca.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufu.facom.bianca.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{
	
	@Transactional(readOnly=true)
	List<Livro> findByNomeContainingIgnoreCase(String nome);
	
	@Transactional(readOnly=true)
	Page <Livro> findByNomeContainingIgnoreCase(String nome, Pageable page); // Retorna a busca de forma paginada
}
