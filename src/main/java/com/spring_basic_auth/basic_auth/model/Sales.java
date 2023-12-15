package com.spring_basic_auth.basic_auth.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int saleId;
    private int buyerId;
    private int sellerId;
    private int carId;
    private BigDecimal salePrice;
    private boolean saleCompleted;

    public Sales(int buyerId, int sellerId, int carId, BigDecimal salePrice, boolean saleCompleted) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.carId = carId;
        this.salePrice = salePrice;
        this.saleCompleted = saleCompleted;
    }

    public Sales() {
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public void setSaleCompleted(boolean saleCompleted) {
        this.saleCompleted = saleCompleted;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getCarId() {
        return carId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public boolean isSaleCompleted() {
        return saleCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return saleId == sales.saleId && buyerId == sales.buyerId && sellerId == sales.sellerId && carId == sales.carId && saleCompleted == sales.saleCompleted && Objects.equals(salePrice, sales.salePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId, buyerId, sellerId, carId, salePrice, saleCompleted);
    }

    @Override
    public String toString() {
        return "Sales{" +
                "saleId=" + saleId +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", carId=" + carId +
                ", salePrice=" + salePrice +
                ", saleCompleted=" + saleCompleted +
                '}';
    }
}