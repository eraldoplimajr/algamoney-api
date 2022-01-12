package com.algaworks.algamoney.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public List<Lancamento> listarLancamentos(){
		return lancamentoRepository.findAll();
	}
	
	public Lancamento buscarLancamentoPorId(Long id) {
		
		Optional<Lancamento> optLancamento = lancamentoRepository.findById(id);
		if(optLancamento.isEmpty()) {
			throw new EmptyResultDataAccessException(1);			
		}

		return optLancamento.get();
	}
	
	public Lancamento incluirLancamento(Lancamento lancamento) {
		return lancamentoRepository.save(lancamento);
	}

}
