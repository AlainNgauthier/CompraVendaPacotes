package br.ufscar.dc.dsw.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.dao.IPurchaseDAO;
import br.ufscar.dc.dsw.domain.Purchase;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.service.spec.IPurchaseService;

@Service
@Transactional(readOnly = false)
public class PurchaseService implements IPurchaseService {

	@Autowired
	IPurchaseDAO dao;
	
	public void salvar(Purchase compra) {
		dao.save(compra);
	}

	@Transactional(readOnly = true)
	public Purchase buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Purchase> buscarTodosPorCliente(Client c) {
		return dao.findAllByCliente(c);
	}
}
