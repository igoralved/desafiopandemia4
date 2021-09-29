package igorgroup.desafiopandemia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import igorgroup.desafiopandemia.model.Atendimento;


public interface AtendimentoRepository  extends JpaRepository<Atendimento, Long>{

	//@Query("SELECT a FROM Atendimento a")
	//List<Atendimento> carregarTodos();
	
	@Query("SELECT min(a.tempoAtendimento) FROM Atendimento a")
	Integer carregarTempoMinimo();
	
	@Query("SELECT max(a.tempoAtendimento) FROM Atendimento a")
	Integer carregarTempoMaximo();
	
	@Query("SELECT avg(a.tempoAtendimento) FROM Atendimento a")
	Double carregarTempoMedio();
	
}
