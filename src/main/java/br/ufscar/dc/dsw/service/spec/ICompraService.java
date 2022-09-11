package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Cliente;

public interface ICompraService {

	Compra buscarPorId(Long id);

	List<Compra> buscarTodosPorCliente(Cliente c);
	
	void salvar(Compra compra);
}
