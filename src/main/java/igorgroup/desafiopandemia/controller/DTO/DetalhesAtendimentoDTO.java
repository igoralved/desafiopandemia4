package igorgroup.desafiopandemia.controller.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import igorgroup.desafiopandemia.model.Atendimento;

public class DetalhesAtendimentoDTO {

	private Long id;
	private String descricao;
	private boolean relacionadoComPandemia = false;
	private boolean semPossibilidadeContagio = false;
	private LocalDate data = LocalDate.now();
	private Integer tempoAtendimento;
	private List<EtapaDTO> etapas;
	private List<TesteDTO> testes;

public DetalhesAtendimentoDTO(Atendimento a) {
	this.id = a.getId();
	this.descricao = a.getDescricao();
	this.relacionadoComPandemia = a.eRealcionadoComPandemia();
	this.semPossibilidadeContagio = a.descartouPossibilidadeContagio();
	this.data = a.getLocalDate();
	this.tempoAtendimento = a.getTempoAtendimento();
	this.etapas.addAll(a.getEtapas().stream().map(EtapaDTO::new).collect(Collectors.toList()));
	this.testes.addAll(a.getTestes().stream().map(TesteDTO::new).collect(Collectors.toList()));
}

public Long getId() {
	return id;
}

public String getDescricao() {
	return descricao;
}

public boolean eRealcionadoComPandemia() {
	return relacionadoComPandemia;
}

public boolean descartouPossibilidadeContagio() {
	return semPossibilidadeContagio;
}

public LocalDate getLocalDate() {
	return data;
}

public String getData() {
	return data.toString();
}

public Integer getTempoAtendimento() {
	return tempoAtendimento;
}

public String toString() {
	String result = "Descrição: " + descricao + ";/n";
	if(relacionadoComPandemia) {
		result += "Está relacionado com pandemia;/n";
	}else {
		result += "Não está relacionado com pandemia;/n";
	}
	if(semPossibilidadeContagio) {
		result += "Descartou possibilidade de contágio;/n";
	}else {
		result += "Não descartou possibilidade de contágio;/n";
	}result += "Tempo: " + tempoAtendimento;
	return result;
}

public void print() {
	System.out.println(toString());
}

public static List<DetalhesAtendimentoDTO> converter(List<Atendimento> lista){
	return lista.stream().map(DetalhesAtendimentoDTO::new).collect(Collectors.toList());
}
	
}
