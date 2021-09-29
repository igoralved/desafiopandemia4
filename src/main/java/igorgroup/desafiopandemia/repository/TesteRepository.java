package igorgroup.desafiopandemia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import igorgroup.desafiopandemia.model.Teste;


public interface TesteRepository extends JpaRepository<Teste, Long>{

	
	@Query("SELECT t FROM Teste t")
	List<Teste> carregarTodas();
	
}
