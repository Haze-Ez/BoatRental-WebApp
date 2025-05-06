
import Ezebuiro.BoatRentalWebApplication;
import Ezebuiro.Entities.Customer;
import Ezebuiro.EntityManager.Config;
import Ezebuiro.Services.CustomerService;
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
public class CustomerServiceTest {

    private CustomerService customerService=null;
    AnnotationConfigApplicationContext context=null;

    @BeforeEach
    void setUp() throws PersistenceException  {
        context = new AnnotationConfigApplicationContext(Config.class);
        customerService = context.getBean(CustomerService.class);
    }

    @Test
    public void testAddCustomer() throws PersistenceException {
        int oldCustomers = customerService.getAllCustomers().size();
        Customer customer= new Customer(0,"Lizzy","Irithyl","bliz.haze@example.com","A0001","UK");
        customerService.addOrUpdateCustomer(customer);
        assertNotNull(customer);
        int newCustomers = customerService.getAllCustomers().size();
        assertTrue(newCustomers == oldCustomers+1);
    }


    @Test
    public void testGetCustomerById() throws PersistenceException {
      Customer customer = customerService.getCustomer(1);
      assertEquals(customer,customerService.getAllCustomers().get(0));
      assertEquals(1,customer.getId());
    }


    @Test
    public void testGetAllCustomers() throws PersistenceException {
        List<Customer> customers = customerService.getAllCustomers();
        assertFalse(customers.isEmpty());
        for(Customer customer : customers) {
            assertNotNull(customer);
        }
        assertTrue(5==customers.size());
    }

    @Test
    public void testUpdateCustomer() throws PersistenceException {
       Customer customer = customerService.getAllCustomers().get(0);
       String former= customer.getBoatLicense();
       customer.setBoatLicense("000A1");
       customerService.addOrUpdateCustomer(customer);
       customer = customerService.getAllCustomers().get(0);
       assertNotEquals(former,customer.getBoatLicense());
       assertEquals("000A1",customer.getBoatLicense());
    }

    @Test
    public void testDeleteCustomer () throws PersistenceException {
        Customer customer= new Customer(0,"Lizzy","Irithyl","bliz.haze@example.com","A0001","UK");
        customerService.addOrUpdateCustomer(customer); //In order to prevent foreign key violations//
        int initialsize= customerService.getAllCustomers().size(); //5+1//
        customerService.deleteCustomer(customerService.getAllCustomers().size()-1);
        int afterdelete = customerService.getAllCustomers().size(); //5//
        assertTrue(afterdelete==initialsize-1); //1 customer deleted//

    }



}
