package br.ufu.facom.bianca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufu.facom.bianca.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
	
	List<Autor> findByNomeContainingIgnoreCase(String nome);
}
