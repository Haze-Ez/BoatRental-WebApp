package Ezebuiro.Services;

import Ezebuiro.Entities.Customer;
import Ezebuiro.Persistence.Custom.Implements.CustomerDAO;
import Ezebuiro.Persistence.IBoatDAO;
import Ezebuiro.Persistence.ICustomerDAO;
import Ezebuiro.Persistence.IRentalEventDAO;
import Ezebuiro.Entities.Boat;
import Ezebuiro.Entities.RentalEvent;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RentalEventService {
    private ICustomerDAO customerDAO;
    private  IRentalEventDAO eventDAO;
    private  IBoatDAO boatDAO;


    @Autowired
    public RentalEventService(IRentalEventDAO eventDAO, IBoatDAO boatDAO, ICustomerDAO customerDAO) {
        this.eventDAO =  eventDAO;
        this.boatDAO =   boatDAO;
        this.customerDAO = customerDAO;
    }

    @Transactional
    public void Rentboat(RentalEvent event) throws PersistenceException {

        try {
            Optional<Boat> boat = boatDAO.findById(event.getBoat_Rented().getId());
            Optional <Customer> customer = customerDAO.findById(event.getCustomer_Renting().getId());
            if(boat.isPresent() && customer.isPresent()) {
                if (boat.get().isAvailable()){

                    // Create the rental event
                    event.setBoat_Rented(boat.get());
                    event.setCustomer_Renting(customer.get());
                    eventDAO.saveAndFlush(event);

                    // Mark the boat as unavailable
                    boat.get().setAvailable(false);
                    boatDAO.saveAndFlush(boat.get());

                } else {
                    throw new IllegalStateException("Boat is not available");
                }
            }

        }catch (PersistenceException e){
            throw new PersistenceException("Service failed to rent a boat: "+e.getMessage(),e);
        }


    }
    public void ReturnBoat(int rentalEventId) {
        RentalEvent event = getRentalEventById(rentalEventId);
        event.setClosed(true);
        Optional <Boat> boat = boatDAO.findById(event.getBoat_Rented().getId());

        boat.get().setAvailable(true);

        boatDAO.saveAndFlush(boat.get());
        eventDAO.saveAndFlush(event);
    }

    public void UpdateEvent(RentalEvent event) throws PersistenceException{
        try{
            eventDAO.saveAndFlush(event);
        }catch (PersistenceException e){
            throw new PersistenceException("Failled to update Event: "+e.getMessage(),e);
        }
    }

    public RentalEvent getRentalEventById(int id) throws PersistenceException {
        Optional<RentalEvent> event0 = eventDAO.findById(id);
        if(event0.isPresent()){
            return event0.get();
        }
        else throw new NoResultException("No Rental event by that Id");
    }


    public List<RentalEvent> getAllRentalEvents() throws PersistenceException {
        return eventDAO.findAll();
    }


    public int eventsByCustomer(int customerId) throws PersistenceException{
        List<RentalEvent> events= eventDAO.eventsByCustomer(customerId);
        try
        {
            System.out.println(events.size()+" event(s) by this customer");
            eventDAO.findAllById(Collections.singleton(customerId));
            return (events.size());
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }



    public void deleteRentalEvent(int id) throws PersistenceException {
       Optional<RentalEvent> event0 = eventDAO.findById(id);
       if(event0.isPresent()){
           eventDAO.deleteById(id);
       }
      else throw new NoResultException("No Rental event by that Id");
    }
}



