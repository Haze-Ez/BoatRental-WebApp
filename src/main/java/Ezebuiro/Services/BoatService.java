package Ezebuiro.Services;


import Ezebuiro.Persistence.IBoatDAO;
import Ezebuiro.Entities.Boat;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {
    private final IBoatDAO boatDAO;

    @Autowired
    public BoatService(IBoatDAO boatDAO) {
        this.boatDAO = boatDAO;
    }


    public long addOrUpdateBoat(Boat boat) throws PersistenceException {
        boatDAO.saveAndFlush(boat);
        return boatDAO.count();

    }


    public List<Boat> getAllBoats() throws PersistenceException {
        return boatDAO.findAll();
    }


    public List<Boat> findByBrand(String brand) throws PersistenceException {
        List<Boat> boats = null;
        try {
            boats= boatDAO.findByBrand(brand);
        } catch (PersistenceException e) {
            e.getMessage();
        }
        return boats;
    }


    public Boat getBoatById(int id) throws PersistenceException {
        Optional<Boat> boat= boatDAO.findById(id);
        if (boat.isPresent()) {
            return boat.get();
        }
        else throw new PersistenceException("Boat not found");
    }


    public List<Boat> advancedsearch(int min, int max, double price, String brand, String model) throws PersistenceException {
        return boatDAO.searchBoats(min, max, price, brand, model);
    }



    public boolean delete(int id) throws PersistenceException {
        Optional<Boat> boat= boatDAO.findById(id);
        if (boat.isPresent()) {
            boatDAO.deleteById(id);
            return true;
        }
        else throw new PersistenceException("Boat not found");
    }
}
