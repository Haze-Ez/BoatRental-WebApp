package Ezebuiro.Persistence;

import Ezebuiro.Entities.Customer;
import Ezebuiro.Persistence.Custom.CustomerRepositoryCustom;
import jakarta.persistence.PersistenceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public interface ICustomerDAO extends JpaRepository<Customer, Integer>, CustomerRepositoryCustom {

}
