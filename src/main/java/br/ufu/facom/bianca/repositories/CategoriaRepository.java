package br.ufu.facom.bianca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufu.facom.bianca.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	@Transactional(readOnly=true)
	List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
