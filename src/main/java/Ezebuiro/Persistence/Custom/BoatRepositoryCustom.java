package Ezebuiro.Persistence.Custom;

import Ezebuiro.Entities.Boat;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public interface BoatRepositoryCustom {

    List<Boat> findByBrand(String brand) throws PersistenceException;
    List<Boat> searchBoats(int minseat, int maxseat, double maxRentPrice, String brand, String model)throws PersistenceException;

}

