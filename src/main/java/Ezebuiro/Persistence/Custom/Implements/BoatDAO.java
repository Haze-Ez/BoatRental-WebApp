package Ezebuiro.Persistence.Custom.Implements;

import Ezebuiro.Persistence.Custom.BoatRepositoryCustom;
import Ezebuiro.Entities.Boat;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class BoatDAO implements BoatRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Boat> findByBrand(String brand) {
        try {
            TypedQuery<Boat> query = entityManager.createQuery("SELECT b FROM Boat b WHERE b.brand = :brand", Boat.class);
            return query.setParameter("brand", brand).getResultList();
        } catch (PersistenceException e) {
            throw new PersistenceException("Failed to retrieve boats with that brand: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Boat> searchBoats(int minseat, int maxseat, double maxRentPrice, String brand, String model) throws PersistenceException {
        try {
            return entityManager.createQuery("SELECT b FROM Boat b WHERE b.seats BETWEEN :minseat AND :maxseat AND b.pricePerDay <= :maxRentPrice AND b.brand = :brand AND b.model = :model")
                    .setParameter("minseat", minseat)
                    .setParameter("maxseat", maxseat)
                    .setParameter("maxRentPrice", maxRentPrice)
                    .setParameter("brand", brand)
                    .setParameter("model", model)
                    .getResultList();
        } catch (PersistenceException e) {
            throw new PersistenceException("Failed to search boats: " + e.getMessage(), e);
        }

    }
}