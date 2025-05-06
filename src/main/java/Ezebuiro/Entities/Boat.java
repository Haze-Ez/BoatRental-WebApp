package Ezebuiro.Entities;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "Boat")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;
    private double pricePerDay;
    private int seats;
    private String plateNumber;
    private boolean available;

    public Boat(){}

    public Boat(int id, String brand, String model, double pricePerDay, int seats, String plateNumber, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.seats = seats;
        this.plateNumber = plateNumber;
        this.available = available;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    @Override
    public String toString() {
        return "Boat{-- " +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", available=" + available +
                ", pricePerDay=" + pricePerDay +
                ", seats=" + seats +
                ", plateNumber='" + plateNumber + '\'' +
                "-- }";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Boat boat = (Boat) obj;
        return id == boat.id &&
                Double.compare(boat.pricePerDay, pricePerDay) == 0 &&
                seats == boat.seats &&
                available == boat.available &&
                plateNumber.equals(boat.plateNumber) &&
                brand.equals(boat.brand) &&
                model.equals(boat.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, available, pricePerDay, seats, plateNumber);
    }

}
