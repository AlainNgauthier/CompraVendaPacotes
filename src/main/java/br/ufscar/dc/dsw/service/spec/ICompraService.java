package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Client;

public interface ICompraService {

	Compra buscarPorId(Long id);

	List<Compra> buscarTodosPorCliente(Client c);
	
	void salvar(Compra compra);
}
