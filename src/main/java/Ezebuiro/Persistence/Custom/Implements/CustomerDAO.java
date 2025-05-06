package Ezebuiro.Persistence.Custom.Implements;

import Ezebuiro.Persistence.Custom.CustomerRepositoryCustom;
import Ezebuiro.Persistence.ICustomerDAO;
import Ezebuiro.Entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class CustomerDAO implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer getByName(String firstname,String lastname) throws PersistenceException {
        try {
            return (Customer) entityManager.createQuery("SELECT c FROM Customer c WHERE LOWER(c.firstName) = :firstname AND LOWER(c.lastName) = :lastname")
                    .setParameter("firstname", firstname)
                    .setParameter("lastname", lastname)
                    .getSingleResult();
        } catch (PersistenceException e) {
            throw new PersistenceException("Failed to find customer: " + e.getMessage(), e);
        }
    }

}








