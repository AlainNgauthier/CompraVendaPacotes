package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Agencia;

@SuppressWarnings("unchecked")
public interface IAgencyDAO extends CrudRepository<Agencia, Long>{
    Agencia findById(long id);
    Agencia findByCnpj(String cnpj);
    Agencia findByNome(String nome);
	Agencia save(Agencia agencia);
	void deleteById(Long id);
	List<Agencia> findAll();
}
