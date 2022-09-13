package br.ufscar.dc.dsw.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import br.ufscar.dc.dsw.domain.User;

@SuppressWarnings("unchecked")
public interface IUserDAO extends CrudRepository<User, Long>{
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);

}
