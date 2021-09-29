
package igorgroup.desafiopandemia.controller.form;
import java.util.List;



import igorgroup.desafiopandemia.model.Teste;
import igorgroup.desafiopandemia.repository.TesteRepository;

public class TesteForm {

	
	private int numero;
	private String resultado;
	
	public int getNumero() {
		return numero;
	}
	
	public String getResultado() {
		return resultado;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public Teste converter() {
		return new Teste();
	}
	
	
	
	public Teste atualizar(Long id, TesteRepository tr) {
		List<Teste> lista = tr.findAll();
		for(Teste t : lista) {
			if(t.getId() == id) {
				t.setNumero(this.numero);
				t.setResultado(this.resultado);
				return t;
			}
		}return null;
	}
	
}
