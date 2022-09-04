package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.domain.Pacote;

public interface IPacoteService {
    Pacote buscarPorId(Long id);
    List<Pacote> buscarTodos();
	void salvar(Pacote pacote);
	void excluir(Long id);
}
