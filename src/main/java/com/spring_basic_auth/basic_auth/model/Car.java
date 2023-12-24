package com.spring_basic_auth.basic_auth.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int listingId;
    private String city;
    private String state;
    private String color;
    private String drivetrain;
    private int mpg;
    private String transmission;
    private String engine;
    private String vin;
    private String mileage;
    private BigDecimal price;

    public Car() {
    }

    public Car( String city, String state, String color, String drivetrain, int mpg, String transmission, String engine, String vin, String mileage, BigDecimal price) {

        this.city = city;
        this.state = state;
        this.color = color;
        this.drivetrain = drivetrain;
        this.mpg = mpg;
        this.transmission = transmission;
        this.engine = engine;
        this.vin = vin;
        this.mileage = mileage;
        this.price = price;
    }


    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public int getMpg() {
        return mpg;
    }

    public void setMpg(int mpg) {
        this.mpg = mpg;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return listingId == car.listingId && mpg == car.mpg && Objects.equals(city, car.city) && Objects.equals(state, car.state) && Objects.equals(color, car.color) && Objects.equals(drivetrain, car.drivetrain) && Objects.equals(transmission, car.transmission) && Objects.equals(engine, car.engine) && Objects.equals(vin, car.vin) && Objects.equals(mileage, car.mileage) && Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listingId, city, state, color, drivetrain, mpg, transmission, engine, vin, mileage, price);
    }

    @Override
    public String toString() {
        return "Car{" +
                "listingId=" + listingId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", color='" + color + '\'' +
                ", drivetrain='" + drivetrain + '\'' +
                ", mpg=" + mpg +
                ", transmission='" + transmission + '\'' +
                ", engine='" + engine + '\'' +
                ", vin='" + vin + '\'' +
                ", mileage='" + mileage + '\'' +
                ", price=" + price +
                '}';
    }
}