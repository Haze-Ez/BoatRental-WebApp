package Ezebuiro.controller;

import Ezebuiro.Entities.Boat;
import Ezebuiro.Entities.Customer;
import Ezebuiro.Services.BoatService;
import Ezebuiro.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Get all customers
    @GetMapping()
    public List<Customer> getAllCustomers() throws SQLException {
        return customerService.getAllCustomers();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) throws SQLException {
        return customerService.getCustomer(id);
    }

    // Get customer by name
    @GetMapping("/name/")
    public Customer getCustomerByName(
            @RequestParam String firstname,
            @RequestParam String lastname
    ) throws SQLException {
        return customerService.getCustomer_name(firstname, lastname);
    }

    // Create new customer
    @PostMapping()
    public Customer createCustomer(@RequestBody Customer customer) throws SQLException {
        customerService.addOrUpdateCustomer(customer);
        return customer;  // Returning the customer as is, since addCustomer doesn't return anything
    }

    // Update existing customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customer) throws SQLException {
        customer.setId(id);  // Ensure the ID from path is used
        customerService.addOrUpdateCustomer(customer);
        return customer;
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id) throws SQLException {
        customerService.deleteCustomer(id);
    }
}
