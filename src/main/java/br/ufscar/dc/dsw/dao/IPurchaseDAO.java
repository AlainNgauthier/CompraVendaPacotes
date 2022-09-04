package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Purchase;
import br.ufscar.dc.dsw.domain.Client;

@SuppressWarnings("unchecked")
public interface IPurchaseDAO extends CrudRepository<Purchase, Long>{

	Purchase findById(long id);

	List<Purchase> findAllByCliente(Client c);
	
	Purchase save(Purchase compra);
}