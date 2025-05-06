package Ezebuiro.controller;


import Ezebuiro.Entities.Boat;
import Ezebuiro.Services.BoatService;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/boats")
public class BoatController {

    @Autowired
    private BoatService boatService;

    // Get all boats
    @GetMapping()
    public List<Boat> getAllBoats() throws PersistenceException {
        return boatService.getAllBoats();
    }

    // Get boat by ID
    @GetMapping("/{id}")
    public Boat getBoatById(@PathVariable int id) throws PersistenceException {
        return boatService.getBoatById(id);
    }

    // Get boats by brand
    @GetMapping("/brand/{brand}")
    public List<Boat> getBoatsByBrand(@PathVariable String brand) {
        return boatService.findByBrand(brand);
    }

    // Create new boat
    @PostMapping()
    public Boat createBoat(@RequestBody Boat boat) throws PersistenceException {
        boatService.addOrUpdateBoat(boat);
        return boat;  // Returning the boat as is, since addBoat doesn't return anything
    }

    //Update Boat
    @PutMapping("/{id}")
    public Boat updateBoat(@PathVariable int id, @RequestBody Boat boat) throws PersistenceException {
        boat.setId(id);  // Ensure the ID matches the path
        boatService.addOrUpdateBoat(boat);
        return boat;
    }

    // Delete boat
    @DeleteMapping("/{id}")
    public void deleteBoat(@PathVariable int id) throws PersistenceException {
        boatService.delete(id);
    }

    // Advanced search
    @GetMapping("/search")
    public List<Boat> advancedSearch(
            @RequestParam int min,
            @RequestParam int max,
            @RequestParam double price,
            @RequestParam String brand,
            @RequestParam String model) throws PersistenceException {
        return boatService.advancedsearch(min, max, price, brand, model);
    }
}
