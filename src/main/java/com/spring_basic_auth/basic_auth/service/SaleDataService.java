package com.spring_basic_auth.basic_auth.service;

import com.spring_basic_auth.basic_auth.dtomodel.SaleDTO;
import com.spring_basic_auth.basic_auth.model.Car;
import com.spring_basic_auth.basic_auth.model.Sales;
import com.spring_basic_auth.basic_auth.repository.CarRepository;
import com.spring_basic_auth.basic_auth.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleDataService {
    public final CarRepository carRepository;
    public final SalesRepository salesRepository;

    public SaleDataService(CarRepository carRepository, SalesRepository salesRepository) {
        this.carRepository = carRepository;
        this.salesRepository = salesRepository;
    }
    public Optional<Car> findById(int id){
        return carRepository.findById(id);
    }
    public void save(Car car) {
        carRepository.save(car);
    }
    public void deleteCarById(int id){
        carRepository.deleteById(id);
    }

    public List<Sales> getAllSales() {
        return (List< Sales >) salesRepository.findAll();
    }
    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }
    public Optional<Sales> findSaleById(int id) {
        return salesRepository.findById(id);
    }
    public void saveSale(Sales sales) {
        salesRepository.save(sales);
    }
    public void deleteSaleById(int id) {
        salesRepository.deleteById(id);
    }

    public void deleteAllCars(){
        carRepository.deleteAll();
    }
    public void deleteAllSales(){
        salesRepository.deleteAll();
    }

    public Sales createSale(SaleDTO saleDTO) {
        validateSaleDTO(saleDTO);
        Sales sales = mapDTOToSales(saleDTO);
        return salesRepository.save(sales);
    }

    private void validateSaleDTO(SaleDTO saleDTO) {
        if (saleDTO.getBuyerId() <= 0 || saleDTO.getSellerId() <= 0 ||
                saleDTO.getCarId() <= 0 || saleDTO.getSalePrice() == null) {
            throw new IllegalArgumentException("Incomplete data in SaleDTO. All fields are mandatory.");
        }
    }

    private Sales mapDTOToSales(SaleDTO saleDTO) {
        Sales sales = new Sales();
        sales.setBuyerId(saleDTO.getBuyerId());
        sales.setSellerId(saleDTO.getSellerId());
        sales.setCarId(saleDTO.getCarId());
        sales.setSalePrice(saleDTO.getSalePrice());
        sales.setSaleCompleted(saleDTO.isSaleCompleted());
        return sales;
    }
}
