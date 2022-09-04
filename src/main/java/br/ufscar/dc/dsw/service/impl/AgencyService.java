package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IAgencyDAO;
import br.ufscar.dc.dsw.domain.Agency;
import br.ufscar.dc.dsw.service.spec.IAgencyService;

@Service
@Transactional(readOnly = false)
public class AgencyService implements IAgencyService {
    
	@Autowired
	IAgencyDAO dao;
	
	public void salvar(Agency agencia) {
		dao.save(agencia);
	}
	
	public void excluir(Long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Agency buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Agency> buscarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public boolean temPacotes(Long id) {
		return !dao.findById(id.longValue()).getPacotes().isEmpty(); 
	}
}
