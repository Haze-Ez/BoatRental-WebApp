package Ezebuiro.controller;

import Ezebuiro.Entities.RentalEvent;
import Ezebuiro.Services.RentalEventService;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/rentalevents")
public class RentalEventController {

    @Autowired
    private RentalEventService rentalEventService;

    // Get all rental events
    @GetMapping()
    public List<RentalEvent> getAllRentalEvents() throws PersistenceException {
        return rentalEventService.getAllRentalEvents();
    }

    // Get rental event by ID
    @GetMapping("/{id}")
    public RentalEvent getRentalEventById(@PathVariable int id) throws PersistenceException {
        return rentalEventService.getRentalEventById(id);
    }

    // Create new rental event (rent a boat)
    @PostMapping("/rent")
    public RentalEvent rentBoat(@RequestBody RentalEvent rentalEvent) throws PersistenceException {
        rentalEventService.Rentboat(rentalEvent);
        return rentalEvent;  // Return the rental event that was processed
    }
/*
    // Return a rented boat
    @PostMapping("/return/{id}")
    public RentalEvent returnBoat(@PathVariable int id) throws PersistenceException {
        RentalEvent rentalEvent = rentalEventService.getRentalEventById(id);
        rentalEventService.returnBoat(rentalEvent);
        return rentalEvent;  // Return the updated rental event
    }
*/
    // Update rental event
    @PutMapping("/{id}")
    public RentalEvent updateRentalEvent(@PathVariable int id, @RequestBody RentalEvent rentalEvent) throws PersistenceException {
        rentalEvent.setId(id);  // Ensure the ID from path is used
        rentalEventService.UpdateEvent(rentalEvent);
        return rentalEvent;
    }

    @GetMapping("/customer/{customerId}")
    public int eventsByCustomer(@PathVariable int customerId) throws PersistenceException{
        return rentalEventService.eventsByCustomer(customerId);
    }

    // Delete rental event
    @DeleteMapping("/{id}")
    public void deleteRentalEvent(@PathVariable int id) throws PersistenceException  {
        rentalEventService.deleteRentalEvent(id);
    }
}

