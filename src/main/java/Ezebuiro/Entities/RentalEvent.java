package Ezebuiro.Entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "RentalEvent")
public class RentalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "boatId", nullable = false)
    private Boat boat_Rented;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer_Renting;

    private Date rentalDate;
    private Date returnDate;
    private double totalCost;
    private boolean isClosed;

    public RentalEvent(){}

    public RentalEvent(int id, Boat boat, Customer customer, Date rentalDate, Date returnDate, Double totalCost, boolean isClosed) {
        this.id = id;
        this.boat_Rented = boat;
        this.customer_Renting = customer;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
        this.isClosed = isClosed;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boat getBoat_Rented() {
        return boat_Rented;
    }

    public void setBoat_Rented(Boat boat_Rented) {
        this.boat_Rented = boat_Rented;
    }

    public Customer getCustomer_Renting() {
        return customer_Renting;
    }

    public void setCustomer_Renting(Customer customer_Renting) {
        this.customer_Renting = customer_Renting;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return "RentalEvent { " +
                "id=" + id +
                ", boat_Rented=" + boat_Rented.getId() +
                ", customer_Renting=" + customer_Renting.getId() +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", totalCost=" + totalCost +
                ", isClosed=" + isClosed +
                " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RentalEvent event = (RentalEvent) obj;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}