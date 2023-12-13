package com.spring_basic_auth.basic_auth.dtomodel;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class SaleDTO {
    @NotNull(message = "Buyer id should not be empty!")
    @Min(value = 1, message = "Buyer id should be at least 1")
    private Integer buyerId;

    @NotNull(message = "Seller id should not be empty!")
    @Min(value = 1, message = "Seller id should be at least 1")
    private Integer sellerId;

    @NotNull(message = "Car id should not be empty!")
    @Min(value = 1, message = "Car id should be at least 1")
    private Integer carId;

    @NotNull(message = "Sale price should not be empty!")
    @DecimalMin(value = "1.00", message = "Sale price must be greater than or equal to 1")
    private BigDecimal salePrice;

    private boolean saleCompleted;

    public SaleDTO(Integer buyerId, Integer sellerId, Integer carId, BigDecimal salePrice, boolean saleCompleted) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.carId = carId;
        this.salePrice = salePrice;
        this.saleCompleted = saleCompleted;
    }

    public SaleDTO() {
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isSaleCompleted() {
        return saleCompleted;
    }

    public void setSaleCompleted(boolean saleCompleted) {
        this.saleCompleted = saleCompleted;
    }
    @Override
    public String toString() {
        return "SaleDTO{" +
                "buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", carId=" + carId +
                ", salePrice=" + salePrice +
                ", saleCompleted=" + saleCompleted +
                '}';
    }
}
