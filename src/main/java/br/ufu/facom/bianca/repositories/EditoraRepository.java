package br.ufu.facom.bianca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufu.facom.bianca.domain.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer>{
	
	List<Editora> findByNomeContainingIgnoreCase(String nome); 
}
