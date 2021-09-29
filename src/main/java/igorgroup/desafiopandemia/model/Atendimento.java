package igorgroup.desafiopandemia.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //@Table(name = "Atendimento")
public class Atendimento {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private boolean relacionadoComPandemia = false;
	private boolean semPossibilidadeContagio = false;
	private Integer tempoAtendimento;

	private LocalDate data = LocalDate.now();
	
	@OneToMany
	private List<Etapa> etapas = new ArrayList<>();
	@OneToMany
	private List<Teste> testes = new ArrayList<>();
	/*
	public Atendimento() {
		
	}
	
	public Atendimento(String desc, boolean rcp, boolean spc, Integer ta) {
		this.descricao = desc;
		this.relacionadoComPandemia = rcp;
		this.semPossibilidadeContagio = spc;
		this.tempoAtendimento = ta;
	}*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}if(obj == null) {
			return false;
		}if(getClass() != obj.getClass()) {
			return false;
		}UnidadeSaude outra = (UnidadeSaude) obj;
		if(id == null) {
			if(outra.getId() != null) {
				return false;
			}
		}else if(!getId().equals(outra.getId())) {
			return false;
		}return true;
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
	
	@OneToMany
	public List<Etapa> getEtapas(){
		return this.etapas;
	}
	
	@OneToMany
	public List<Teste> getTestes(){
		return  this.testes;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void relacionaComPandemia() {
		this.relacionadoComPandemia = true;
	}
	
	public void desRelacionaComPandemia() {
		this.relacionadoComPandemia = false;
	}
	
	public void descartaPossibilidadeContagio() {
		this.semPossibilidadeContagio = true;
	}
	
	public void colocaPossibilidadeContagio() {
		this.semPossibilidadeContagio = false;
	}
	
	public void setData(int anos, int meses, int dias) {
		data = LocalDate.of(anos, meses, dias);
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public void setTempoAtendimento(Integer tempo) {
		this.tempoAtendimento = tempo;
	}
	
	public void addEtapa(Etapa e) {
		etapas.add(e);
	}
	
	public void addTeste(Teste t) {
		if(testes.size() < 2) {
			testes.add(t);
		}
	}
	
	public void setEtapas(List<Etapa> lista) {
		this.etapas = lista;
	}
	
	public void setTestes(List<Teste> lista) {
		this.testes = lista;
	}
	
	public void descartaOuNaoDescarta(boolean simounao) {
		this.semPossibilidadeContagio = simounao;
	}
	
	public void relacionaOuNao(boolean simounao) {
		this.relacionadoComPandemia = simounao;
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
	
}
