package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Agency;

public interface IAgencyService {
	Agency buscarPorId(Long id);
    List<Agency> buscarTodos();
	void salvar(Agency agencia);
	void excluir(Long id);
	boolean temPacotes(Long id);
}
