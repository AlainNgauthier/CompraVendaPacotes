package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{
    Cliente findById(long id);
    Cliente findByCpf(String cpf);
	Cliente save(Cliente agencia);
	List<Cliente> findAll();
	void deleteById(Long id);
    
    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    public Cliente getUserByEmail(@Param("email") String email);
}
