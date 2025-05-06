package Ezebuiro.Persistence.Custom;

import Ezebuiro.Entities.Customer;
import jakarta.persistence.PersistenceException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepositoryCustom {
    @Query("SELECT c FROM Customer c WHERE LOWER(c.firstName) = :firstname AND LOWER(c.lastName) = :lastname")
    Customer getByName(@Param("firstname")  String firstname,
                       @Param("lastname") String lastname) throws PersistenceException;

}
