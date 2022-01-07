package com.algaworks.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney.api.PessoaService;
import com.algaworks.algamoney.api.model.Pessoa;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {

	@Autowired
	PessoaService pessoaService;
	
	@GetMapping
	public List<Pessoa> buscarTodasPessoas() {
		return pessoaService.listarTodasPessoas();		
	}
	
	@GetMapping("/{id}")
	public Pessoa buscarPessoa(@PathVariable Long id) {
		return pessoaService.buscarPessoaPorId(id).get();		
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> incluirPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaBD = pessoaService.incluirPessoa(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoaBD.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(pessoaBD);
	}
	
}
