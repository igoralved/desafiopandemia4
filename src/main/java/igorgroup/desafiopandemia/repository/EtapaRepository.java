package igorgroup.desafiopandemia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import igorgroup.desafiopandemia.model.Etapa;


public interface EtapaRepository extends JpaRepository<Etapa, Long> {

	@Query("SELECT e FROM Etapa e WHERE (:numero is null or e.numero = :numero)")
	List<Etapa> findByNumero(@Param("numero") String numero);
	
	
	
}
