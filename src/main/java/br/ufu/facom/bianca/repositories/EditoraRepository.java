package br.ufu.facom.bianca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufu.facom.bianca.domain.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer>{
	
	@Transactional(readOnly=true)
	List<Editora> findByNomeContainingIgnoreCase(String nome); 
}
