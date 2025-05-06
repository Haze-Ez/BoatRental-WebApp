import Ezebuiro.BoatRentalWebApplication;
import Ezebuiro.Entities.Boat;
import Ezebuiro.EntityManager.Config;
import Ezebuiro.Services.BoatService;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BoatRentalWebApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BoatServiceTest {

    private BoatService boatService=null;

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext(Config.class);
        boatService = context.getBean(BoatService.class);
    }


    @Test
    public void testAddBoat() throws PersistenceException {
        int oldBoats = boatService.getAllBoats().size();
        Boat boat = new Boat(0, "Tank", "XPV-311", 400.0, 350, "ABAB1243", true);
        boatService.addOrUpdateBoat(boat);
        int newBoats = boatService.getAllBoats().size();
        assertEquals(oldBoats + 1, newBoats);
    }

    @Test
    public void getAllBoats() {
        List<Boat> boats = boatService.getAllBoats();
        assertFalse(boats.isEmpty());
        boats.forEach(boat -> assertNotNull(boat));
    }

    @Test
    public void getBoatById() {
        Boat boat = boatService.getBoatById(1);
        assertNotNull(boat);
        assertEquals(1, boat.getId());
    }

    @Test
    public void getBoatByBrand() {
        List<Boat> boats = boatService.findByBrand("Bayliner");
        assertFalse(boats.isEmpty());
        boats.forEach(boat -> assertEquals("Bayliner", boat.getBrand()));
    }

    @Test
    public void updateBoatAvailability() {
        Boat boat = boatService.getAllBoats().get(0);
        boolean original = boat.isAvailable();
        boat.setAvailable(!original);
        boatService.addOrUpdateBoat(boat);
        Boat updated = boatService.getBoatById(boat.getId());
        assertNotEquals(original, updated.isAvailable());
    }

    @Test
    public void testDeleteBoat() {
        Boat boat = new Boat(0, "Temp", "TEST-999", 123.0, 200, "TEST1234", true);
        boatService.addOrUpdateBoat(boat);
        int sizeBefore = boatService.getAllBoats().size();
        boatService.delete(boat.getId());
        int sizeAfter = boatService.getAllBoats().size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void testAdvancedSearch() {
        List<Boat> result = boatService.advancedsearch(6, 10, 400.0, "Yamaha", "SX210");
        assertNotNull(result);
    }
}
