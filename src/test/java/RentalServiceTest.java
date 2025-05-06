
import Ezebuiro.BoatRentalWebApplication;
import Ezebuiro.Entities.Boat;
import Ezebuiro.Entities.RentalEvent;
import Ezebuiro.EntityManager.Config;
import Ezebuiro.Services.*;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

@SpringBootTest(classes = BoatRentalWebApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RentalServiceTest {
    AnnotationConfigApplicationContext context = null;

    private RentalEventService rentalEventService=null;
    private BoatService boatService=null;
    private CustomerService customerService=null;

    @BeforeEach
    void setUp() throws PersistenceException {
        context = new AnnotationConfigApplicationContext(Config.class);
        rentalEventService=context.getBean(RentalEventService.class);
        boatService = context.getBean(BoatService.class);
        customerService = context.getBean(CustomerService.class);
    }

    @Test
    public void TestAddEvent() throws PersistenceException {
        int oldEvents = rentalEventService.getAllRentalEvents().size();
        Boat testBoat = boatService.getBoatById(3);
        testBoat.setAvailable(true);
        boatService.addOrUpdateBoat(testBoat);

        RentalEvent event = new RentalEvent(0,boatService.getBoatById(3), customerService.getCustomer(1), Date.valueOf("2025-02-10"), Date.valueOf("2025-02-12"), 560.0, false);
        rentalEventService.Rentboat(event);

        int newEvents = rentalEventService.getAllRentalEvents().size();
        assertTrue(newEvents == oldEvents+1);

    }


    @Test
    public void getAllEvents() throws PersistenceException {
        List<RentalEvent> events = rentalEventService.getAllRentalEvents();
        int numevents = events.size();
        assertFalse(events.isEmpty());
        for( RentalEvent event : events) {
            assertNotNull(event);
        }
        assertTrue(numevents == 3);//50 Events//
    }

    @Test
    void TestGetEventById() throws PersistenceException  {
        RentalEvent event = rentalEventService.getRentalEventById(1);
        assertNotNull(event);
        assertEquals(1,event.getId());
        assertEquals(event,rentalEventService.getAllRentalEvents().get(0));
    }

    @Test
    void TestUpdateEvent() throws PersistenceException  {
        Boat boat = boatService.getBoatById(3);
        if (!boat.isAvailable()) {
            boat.setAvailable(true);
            boatService.addOrUpdateBoat(boat);
            boat = boatService.getBoatById(3);
        }

        // Create a new rental event
        RentalEvent event = new RentalEvent(0, boatService.getBoatById(3), customerService.getCustomer(1), Date.valueOf("2025-02-10"), null, 0.0, false);
        rentalEventService.Rentboat(event);

        // Refresh event list
        rentalEventService.getAllRentalEvents();
        RentalEvent event00=rentalEventService.getAllRentalEvents().get(rentalEventService.getAllRentalEvents().size()-1); //The added Event at this point

        //Set new values
        event.setReturnDate(Date.valueOf("2025-02-14"));
        event.setClosed(true);
        event.setTotalCost(1200.0);

        // Perform event update
        rentalEventService.UpdateEvent(event);

        // Re-fetch the event after update
        rentalEventService.getAllRentalEvents();

        // Validate the update
        assertNotEquals(event00.getTotalCost(), event.getTotalCost()); //Not null
        assertNotSame(event00,event);
        assertNotEquals(event00.getReturnDate(), event.getReturnDate()); //Not null

        //Validate new event values
        assertEquals(Date.valueOf("2025-02-14"),event.getReturnDate());
        assertTrue(event.isClosed());
        assertEquals(1200.0, event.getTotalCost(), 0.01);
    }

    @Test
    void testReturnBoat() throws PersistenceException{
        Boat boat = boatService.getBoatById(3);
        if (!boat.isAvailable()) {
            boat.setAvailable(true);
            boatService.addOrUpdateBoat(boat);
            boat = boatService.getBoatById(3);
        }

        RentalEvent event = new RentalEvent(0,boat,customerService.getCustomer(1), Date.valueOf("2025-02-10"), null, 0.0, false);
        rentalEventService.Rentboat(event);
        rentalEventService.getAllRentalEvents();//Refresh the list

        assertFalse(boatService.getBoatById(3).isAvailable());

        rentalEventService.ReturnBoat(4);
        rentalEventService.getAllRentalEvents();//Refresh the list
        boatService.getAllBoats(); //Refresh the list

        event=rentalEventService.getAllRentalEvents().get(rentalEventService.getAllRentalEvents().size()-1);
        assertTrue(boatService.getBoatById(3).isAvailable());
        assertTrue(event.isClosed());
    }

    @Test
    void testDeleteEvent () throws PersistenceException  {
        Boat boat = boatService.getBoatById(3);
        if (!boat.isAvailable()) {
            boat.setAvailable(true);
            boatService.addOrUpdateBoat(boat);
            boat = boatService.getBoatById(3);
        }

        RentalEvent event = new RentalEvent(0, boatService.getBoatById(3), customerService.getCustomer(1), Date.valueOf("2025-02-10"), null, 0.0, false);
        rentalEventService.Rentboat(event);
        int initialsize= rentalEventService.getAllRentalEvents().size(); //3+1//
        rentalEventService.deleteRentalEvent(rentalEventService.getAllRentalEvents().size()-1);
        int afterdelete = rentalEventService.getAllRentalEvents().size(); //3//
        assertTrue(afterdelete==initialsize-1); //1 event deleted//

    }

}
