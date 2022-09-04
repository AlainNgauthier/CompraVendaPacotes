package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Agency;

@SuppressWarnings("unchecked")
public interface IAgencyDAO extends CrudRepository<Agency, Long>{
	Agency findById(long id);
	Agency findByCnpj(String cnpj);
	Agency findByNome(String nome);
	List<Agency> findAll();
	Agency save(Agency agencia);
	void deleteById(Long id);
}
