package com.algaworks.algamoney.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Optional<Pessoa> buscarPessoaPorId(Long id){
		return pessoaRepository.findById(id);
	}
	
	public List<Pessoa> listarTodasPessoas(){
		return pessoaRepository.findAll();
	}
	
	public Pessoa incluirPessoa(Pessoa pessoa) {		
		return pessoaRepository.save(pessoa);		
	}
	
	public void removerPessoa(Long codigo) {		
		pessoaRepository.deleteById(codigo);		
	}
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {		
		
		Pessoa pessoaBD = buscarPessoaPeloCodigo(codigo);		
		
		BeanUtils.copyProperties(pessoa, pessoaBD, "codigo");
		pessoaRepository.save(pessoaBD);
		
		return pessoaBD;
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaBD = buscarPessoaPeloCodigo(codigo);
		pessoaBD.setAtivo(ativo);		
		pessoaRepository.save(pessoaBD);		
	}

	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> optPessoa = pessoaRepository.findById(codigo);
		
		if(optPessoa.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return optPessoa.get();
	}
}
