package igorgroup.desafiopandemia.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import igorgroup.desafiopandemia.controller.DTO.UnidadeSaudeDTO;
import igorgroup.desafiopandemia.controller.form.UnidadeSaudeForm;
import igorgroup.desafiopandemia.model.Atendimento;
import igorgroup.desafiopandemia.model.UnidadeSaude;
import igorgroup.desafiopandemia.repository.UnidadeSaudeRepository;

@RestController
@RequestMapping("/unidades")
public class UnidadeSaudeController {

	@Autowired
	private UnidadeSaudeRepository unidadesauderepository;
	

	//@RequestMapping("/unidadestodas")
	@GetMapping
	public List<UnidadeSaudeDTO> lista(){
		List<UnidadeSaude> lista = unidadesauderepository.findAll();
		return UnidadeSaudeDTO.converter(lista);
	}
	
	@GetMapping("/unidadescresc")
	public List<UnidadeSaudeDTO> listaComNumeroPacientesCrescente(LocalDate data){
		LocalDate passada = data.minusWeeks(1);
		LocalDate proxima = data.plusWeeks(1);
		List<UnidadeSaude> anterior = unidadesauderepository.findByWeek(passada.toString(), data.toString());
		List<UnidadeSaude> atual = unidadesauderepository.findByWeek(data.toString(), proxima.toString());
		if(anterior == null || anterior.size() == 0 || atual == null || atual.size() == 0) {
			return null;
		}
		List<UnidadeSaudeDTO> crescentes = new ArrayList<>();
		for(UnidadeSaude u1: anterior) {
			for(UnidadeSaude u2 : atual) {
				if(u1.getNome().equals(u2.getNome())) {
					if(u1.getNumeroPacientes() < u2.getNumeroPacientes()) {
						crescentes.add(new UnidadeSaudeDTO(u2));
					}
				}
			}
		}
		return crescentes;
	}
	
	@RequestMapping("/unidadesmin")
	public HashMap<UnidadeSaudeDTO, Integer> atendimentosminimos(LocalDate data){
		LocalDate passada = data.minusWeeks(1);
		List<UnidadeSaude> listasemanal = unidadesauderepository.findByWeek(passada.toString(), data.toString());
		if(listasemanal == null) {
			return null;
		}HashMap<UnidadeSaudeDTO, Integer> minimos = new HashMap<>();
		for(UnidadeSaude u : listasemanal) {
			if(u.getAtendimentos() == null || u.getAtendimentos().size() == 0) {
				minimos.put(new UnidadeSaudeDTO(u), 24 * 7);
			}else {
				Integer min = 24 * 7;
				for(Atendimento a : u.getAtendimentos()) {
					if(min > a.getTempoAtendimento()) {
						min = a.getTempoAtendimento();
					}
				}minimos.put(new UnidadeSaudeDTO(u), min);
			}
		}
		return minimos;
	}
	
	@RequestMapping("/unidadesmax")
	public HashMap<UnidadeSaudeDTO, Integer> atendimentosmaximos(LocalDate data){
		LocalDate passada = data.minusWeeks(1);
		List<UnidadeSaude> listasemanal = unidadesauderepository.findByWeek(passada.toString(), data.toString());
		if(listasemanal == null) {
			return null;
		}HashMap<UnidadeSaudeDTO, Integer> maximos = new HashMap<>();
		for(UnidadeSaude u : listasemanal) {
			if(u.getAtendimentos() == null || u.getAtendimentos().size() == 0) {
				maximos.put(new UnidadeSaudeDTO(u), 0);
			}else {
				Integer max = 0;
				for(Atendimento a : u.getAtendimentos()) {
					if(max < a.getTempoAtendimento()) {
						max = a.getTempoAtendimento();
					}
				}maximos.put(new UnidadeSaudeDTO(u), max);
			}
		}
		return maximos;
	}
	
	@RequestMapping("/unidadesmed")
	public HashMap<UnidadeSaudeDTO, Double> atendimentosmedios(LocalDate data){
		LocalDate passada = data.minusWeeks(1);
		List<UnidadeSaude> listasemanal = unidadesauderepository.findByWeek(passada.toString(), data.toString());
		if(listasemanal == null) {
			return null;
		}HashMap<UnidadeSaudeDTO, Double> medios = new HashMap<>();
		for(UnidadeSaude u : listasemanal) {
			if(u.getAtendimentos() == null || u.getAtendimentos().size() == 0) {
				medios.put(new UnidadeSaudeDTO(u), 0.0);
			}else {
				double total = 0;
				for(int i = 0; i < u.getAtendimentos().size(); i++) {
					total += u.getAtendimentos().get(i).getTempoAtendimento();
				}Double media = total * 1.0 / u.getAtendimentos().size();
				medios.put(new UnidadeSaudeDTO(u), media);
			}
		}
		return medios;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UnidadeSaudeDTO> cadastrar(@RequestBody UnidadeSaudeForm uform, 
		UriComponentsBuilder uriBuilder) {
		UnidadeSaude u = uform.converter();
		unidadesauderepository.save(u);
		URI uri = uriBuilder.path("/unidades/{id}").buildAndExpand(u.getId()).toUri();
		return ResponseEntity.created(uri).body(new UnidadeSaudeDTO(u));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UnidadeSaudeDTO> detalhar(@PathVariable Long codigo) {
		Optional<UnidadeSaude> uoptional = unidadesauderepository.findById(codigo);
		if(uoptional.isPresent()) {
			return ResponseEntity.ok(new UnidadeSaudeDTO(uoptional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UnidadeSaudeDTO> atualizar(@PathVariable Long id,
			@RequestBody @Valid UnidadeSaudeForm form){
		Optional<UnidadeSaude> optional = unidadesauderepository.findById(id);
		if(optional.isPresent()) {
			UnidadeSaude u = form.atualizar(id, unidadesauderepository);
			return ResponseEntity.ok(new UnidadeSaudeDTO(u));
		}return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<UnidadeSaude> optional = unidadesauderepository.findById(id);
		if(optional.isPresent()) {
			unidadesauderepository.deleteById(id);
			return ResponseEntity.ok().build();
		}return ResponseEntity.notFound().build();
	}
	
}
