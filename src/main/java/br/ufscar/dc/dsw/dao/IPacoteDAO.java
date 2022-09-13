package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Pacote;

@SuppressWarnings("unchecked")
public interface IPacoteDAO extends CrudRepository<Pacote, Long>{
	List<Pacote> findAll();
    Pacote findById(long id);
	Pacote findAllByAgenciaId(Long agencia);
	Pacote findAllByDestinos(String destinos);
	Pacote save(Pacote pacote);
	void deleteById(Long id);

	@Query("SELECT c FROM Pacote c WHERE c.nome = :nome")
    public Pacote getPacoteByName(@Param("nome") String nome);

	@Query("SELECT c FROM Pacote c WHERE c.id = :id")
    Pacote getPacoteById(@Param("id") Long id);
}
