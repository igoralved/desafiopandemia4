package igorgroup.desafiopandemia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import igorgroup.desafiopandemia.model.UnidadeSaude;


public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaude, Long>{
	
	
	
	
	
	@Query("SELECT u FROM UnidadeSaude u WHERE u.data BETWEEN :begin AND :end")
	List<UnidadeSaude> findByWeek(@Param("begin") String inicio, @Param("end") String fim);
	
}
