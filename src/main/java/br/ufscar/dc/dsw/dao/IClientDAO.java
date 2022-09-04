package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Client;

@SuppressWarnings("unchecked")
public interface IClientDAO extends CrudRepository<Client, Long>{
	Client findById(long id);
    Client findByCpf(String cpf);
    List<Client> findAll();
    Client save(Client agencia);
	void deleteById(Long id);
    
    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    public Client getUserByEmail(@Param("email") String email);
}
