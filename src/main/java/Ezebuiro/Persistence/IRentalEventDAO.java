package Ezebuiro.Persistence;

import Ezebuiro.Entities.RentalEvent;
import Ezebuiro.Persistence.Custom.RentalEventRepositoryCustom;
import jakarta.persistence.PersistenceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public interface IRentalEventDAO extends JpaRepository<RentalEvent, Integer>, RentalEventRepositoryCustom {

   // void returnBoat(RentalEvent rentalEvent) throws PersistenceException;

    List<RentalEvent> eventsByCustomer(int customerId) throws PersistenceException;

}