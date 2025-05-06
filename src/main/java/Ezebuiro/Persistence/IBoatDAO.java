package Ezebuiro.Persistence;

import Ezebuiro.Entities.Boat;
import Ezebuiro.Persistence.Custom.BoatRepositoryCustom;
import jakarta.persistence.PersistenceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IBoatDAO extends JpaRepository<Boat, Integer>, BoatRepositoryCustom {
    List<Boat> findByBrand(String brand) throws PersistenceException;
    @Query("SELECT b FROM Boat b WHERE b.seats BETWEEN :minCap AND :maxCap AND b.pricePerDay <= :maxPrice AND b.brand = :brand AND b.model = :model")
    List<Boat> searchBoats(@Param("minCap") int minCapacity,
                           @Param("maxCap") int maxCapacity,
                           @Param("maxPrice") double maxPrice,
                           @Param("brand") String brand,
                           @Param("model") String model)throws PersistenceException;

}