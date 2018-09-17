package br.ufu.facom.bianca.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufu.facom.bianca.domain.Leitor;

@Repository
public interface LeitorRepository extends JpaRepository<Leitor, Integer>{
	
	@Transactional(readOnly=true)
	List <Leitor> findByNomeContainingIgnoreCase(String nome);
	
	@Transactional(readOnly=true)
	Page <Leitor> findByNomeContainingIgnoreCase(String nome, Pageable page); // Retorna a busca de forma paginada
	
	@Transactional(readOnly=true)
	Leitor findByEmail(String nome);
}
