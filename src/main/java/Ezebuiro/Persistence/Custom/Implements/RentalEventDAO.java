package Ezebuiro.Persistence.Custom.Implements;


import Ezebuiro.Persistence.Custom.RentalEventRepositoryCustom;
import Ezebuiro.Persistence.IBoatDAO;
import Ezebuiro.Persistence.IRentalEventDAO;
import Ezebuiro.Entities.RentalEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalEventDAO implements RentalEventRepositoryCustom {

    private final IBoatDAO boatDAO;
    private final IRentalEventDAO rentalEventDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RentalEventDAO(IBoatDAO boatDAO, IRentalEventDAO rentalEventDAO) {
        this.boatDAO = boatDAO;
        this.rentalEventDAO = rentalEventDAO;
    }

/*
    @Override
    public void returnBoat(RentalEvent rentalEvent) throws PersistenceException {
        if (rentalEvent == null) {
            throw new PersistenceException("Rental event not found.");
        }
        if (rentalEvent.isClosed()) {
            throw new PersistenceException("Rental event is already closed.");
        }
        rentalEvent.setClosed(true);
        rentalEventDAO.saveAndFlush(rentalEvent);

        // Mark the boat as available
        boatDAO.findById(rentalEvent.getBoat_Rented().getId()).ifPresent(boat -> {
            boat.markAvailable(true);
            boatDAO.saveAndFlush(boat);
        });
    }
*/
    @Override
    public List<RentalEvent> eventsByCustomer(int customerId) throws PersistenceException{
        TypedQuery<RentalEvent> query = entityManager.createQuery("SELECT r FROM RentalEvent r WHERE customer_Renting.id = :customerId", RentalEvent.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();

    }


}