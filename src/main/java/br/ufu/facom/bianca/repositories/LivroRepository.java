package br.ufu.facom.bianca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufu.facom.bianca.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{
	List<Livro> findByNomeContainingIgnoreCase(String nome);
}
