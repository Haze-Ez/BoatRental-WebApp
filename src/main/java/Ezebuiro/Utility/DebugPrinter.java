package Ezebuiro.Utility;

import Ezebuiro.Entities.Boat;
import Ezebuiro.Entities.Customer;
import Ezebuiro.Entities.RentalEvent;
import Ezebuiro.Services.BoatService;
import Ezebuiro.Services.CustomerService;
import Ezebuiro.Services.RentalEventService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
@Component
public class DebugPrinter {

    @Autowired
    private BoatService Bservice;

    @Autowired
    private CustomerService Cservice;

    @Autowired
    private RentalEventService Rservice;

    //Test current state of Database
    @PostConstruct
    public void printData() {

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        for (Boat boat1 : Bservice.getAllBoats()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(boat1);
        }
        System.out.println();
        for (Customer customer : Cservice.getAllCustomers()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(customer);
        }

        System.out.println();
        for (RentalEvent rentalEvent : Rservice.getAllRentalEvents()) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(rentalEvent);
        }
        System.out.println();
    }


}
*/