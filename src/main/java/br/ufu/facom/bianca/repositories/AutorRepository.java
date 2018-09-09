package br.ufu.facom.bianca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufu.facom.bianca.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
	
}
