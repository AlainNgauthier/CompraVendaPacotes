package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Client;

public interface IClientService {
	Client buscarPorId(Long id);
    List<Client> buscarTodos();
    void salvar(Client cliente);
    void excluir(Long id);
}
