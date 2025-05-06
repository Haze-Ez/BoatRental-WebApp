package Ezebuiro.Services;

import Ezebuiro.Persistence.ICustomerDAO;
import Ezebuiro.Entities.Customer;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final ICustomerDAO customerDAO;
    @Autowired
    public CustomerService(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }


    public long addOrUpdateCustomer(Customer customer) throws PersistenceException {
        customerDAO.saveAndFlush(customer);
        return customerDAO.count();
    }

    public Customer getCustomer(int id) throws PersistenceException {
         Optional<Customer> customer = customerDAO.findById(id);
         if (customer.isPresent()) {
             return customer.get();
         }
         else throw new PersistenceException("Customer not found by this id");
    }


    public Customer getCustomer_name(String firstname, String lastname) throws PersistenceException {
        return customerDAO.getByName(firstname, lastname);
    }

    @Transactional
    public List<Customer> getAllCustomers() throws PersistenceException {
        return customerDAO.findAll();
    }


    @Transactional
    public void deleteCustomer(int id) throws PersistenceException {
        customerDAO.deleteById(id);
    }
}
