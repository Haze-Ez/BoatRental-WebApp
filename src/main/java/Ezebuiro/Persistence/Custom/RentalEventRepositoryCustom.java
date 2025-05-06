package Ezebuiro.Persistence.Custom;

import Ezebuiro.Entities.RentalEvent;
import jakarta.persistence.PersistenceException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalEventRepositoryCustom {

    //void returnBoat(RentalEvent rentalEvent) throws PersistenceException;

    @Query("SELECT r FROM RentalEvent r WHERE r.customer_Renting.id = :customerId")
    List<RentalEvent> eventsByCustomer(@Param("customerId") int customerId) throws PersistenceException;


}
