import javax.swing.*;

public class RentalCar {
    private String brand;
    private String model;
    private int power;
    private int year;
    private double pricePerDay;
    private boolean availability;

    public RentalCar(String brand, String model, int power, int year, double pricePerDay, boolean availability) {
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.availability = availability;
    }

    private JButton rentButton;

    public void setRentButton(JButton rentButton) {
        this.rentButton = rentButton;
    }

    public void updateRentButtonState() {
        if (rentButton != null) {
            rentButton.setEnabled(availability);
        }
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public int getYear() {
        return year;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setModel(String model) {
        this.model = model;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
        updateRentButtonState();
    }

    public String displayAvailability() {
        return availability ? "Available" : "Not Available";
    }
}
