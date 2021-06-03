package br.org.serratec.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.model.Dependente;
import br.org.serratec.backend.repository.DependenteRepository;

@RestController
@RequestMapping("/dependentes")
public class DependenteController {
	@Autowired
	private DependenteRepository dependenteRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Dependente inserir(@Valid @RequestBody Dependente dependente) {
		return dependenteRepository.save(dependente);
	}
	
	@PostMapping("/todos")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Dependente> inserirVarios(@Valid @RequestBody List<Dependente> dependentes) {
		return dependenteRepository.saveAll(dependentes);
	}

	@GetMapping("{id}")
	public ResponseEntity<Dependente> buscar(@PathVariable Long id) {
		Optional<Dependente> dependente = dependenteRepository.findById(id);
		if (dependente.isPresent()) {
			return ResponseEntity.ok(dependente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<Dependente>> listar() {
		List<Dependente> dependentes = dependenteRepository.findAll();
		return ResponseEntity.ok(dependentes);
	}

	@PutMapping("{id}")
	public ResponseEntity<Dependente> atualizar(@PathVariable Long id, @Valid @RequestBody Dependente dependente) {
		if (!dependenteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dependente.setId(id);
		dependente = dependenteRepository.save(dependente);
		return ResponseEntity.ok(dependente);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Dependente> deletar(@PathVariable Long id) {
		if (!dependenteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dependenteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}