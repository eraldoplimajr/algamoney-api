package com.algaworks.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

import com.algaworks.algamoney.api.PessoaService;
import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.model.Pessoa;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Pessoa> buscarTodasPessoas() {
		return pessoaService.listarTodasPessoas();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPessoa(@PathVariable Long id) {
		
		Optional<Pessoa> optPessoa = pessoaService.buscarPessoaPorId(id);
		
		if(!optPessoa.isPresent()) {
			return ResponseEntity.notFound().build();		
		}
		
		return ResponseEntity.ok(optPessoa.get());
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> incluirPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaBD = pessoaService.incluirPessoa(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaBD.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaBD);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirPessoa(@PathVariable Long codigo) {
		pessoaService.removerPessoa(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
		
		Pessoa pessoaBD = pessoaService.atualizar(codigo, pessoa);
		
		return ResponseEntity.ok(pessoaBD);			
				
	}
	
}
