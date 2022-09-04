package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Purchase;
import br.ufscar.dc.dsw.domain.Client;

public interface IPurchaseService {

	Purchase buscarPorId(Long id);

	List<Purchase> buscarTodosPorCliente(Client c);
	
	void salvar(Purchase compra);
}
