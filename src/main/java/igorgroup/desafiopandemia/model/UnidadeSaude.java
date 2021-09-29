package igorgroup.desafiopandemia.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import igorgroup.desafiopandemia.controller.DTO.AtendimentoDTO;

@Entity
@Table(name = "UnidadeSaude")
public class UnidadeSaude {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Integer numeroPacientes;
	
	private LocalDate data = LocalDate.now();
	
	
	@OneToMany
	private List<Atendimento> atendimentos = new ArrayList<>();
	
		
	
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
	
	public String getNome() {
		return nome;
	}
	
	public Integer getNumeroPacientes() {
		return numeroPacientes;
	}
	
	@OneToMany
	public List<Atendimento> getAtendimentos(){
		return atendimentos;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setAtendimentos(List<Atendimento> lista){
		this.atendimentos = lista;
	}
	
	public void setNumeroPacientes(Integer numeroPacientes) {
		this.numeroPacientes = numeroPacientes;
	}
	
	public String getData() {
		return data.toString();
	}
	
	public LocalDate getLocalDate() {
		return data;
	}
	
	public void setData(int ano, int meses, int dias) {
		this.data = LocalDate.of(ano, meses, dias);
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public void addAtendimento(Atendimento a) {
		atendimentos.add(a);
	}
	
	public String toString() {
		return "Nome: " + nome + ";/n"
				+ "Número de pacientes: " + numeroPacientes + ";/n"
				+ "data: " + getData();
	}
	
	public void print() {
		System.out.println(toString());
	}
	
}
