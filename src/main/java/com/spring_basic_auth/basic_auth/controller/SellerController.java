package com.spring_basic_auth.basic_auth.controller;

import com.spring_basic_auth.basic_auth.dtomodel.CarDTO;
import com.spring_basic_auth.basic_auth.dtomodel.SaleDTO;
import com.spring_basic_auth.basic_auth.dtomodel.StandardResponse;
import com.spring_basic_auth.basic_auth.exception.car_exceptions.CarNotFoundException;
import com.spring_basic_auth.basic_auth.model.Car;
import com.spring_basic_auth.basic_auth.model.Sales;
import com.spring_basic_auth.basic_auth.service.SaleDataService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    public final SaleDataService saleDataService;

    public SellerController(SaleDataService saleDataService) {
        this.saleDataService = saleDataService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> findHealth() {
        try {
            // Add health-check logic here if needed
            logger.info("Health check successful");
            return ResponseEntity.ok("Sale is healthy and running successfully!"); // Adjust response message as needed
        } catch (Exception e) {
            logger.error("Health check failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Health check failed"); // Adjust error response as needed
        }
    }

    @PostMapping("/")
    public ResponseEntity<StandardResponse<Sales>> createSale(@Valid @RequestBody SaleDTO saleDTO) {
        try {
            Sales createdSale = saleDataService.createSale(saleDTO);
            if (createdSale != null) {
                logger.info("Sale created successfully with ID: {}", createdSale.getSaleId());
                StandardResponse<Sales> response = new StandardResponse<>(true, HttpStatus.OK.value(), createdSale, "Sale created successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            logger.error("Failed to create sale");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Failed to create sale"));
        } catch (IllegalArgumentException e) {
            logger.error("Bad request while creating sale: {}", e.getMessage());
            return ResponseEntity.badRequest().body(new StandardResponse<>(false, HttpStatus.BAD_REQUEST.value(), null, e.getMessage()));
        } catch (Exception e) {
            logger.error("An unexpected error occurred while creating sale", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error creating sale"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse<Sales>> getSales(@PathVariable int id) {
        try {
            Optional<Sales> salesOptional = saleDataService.findSaleById(id);
            if (salesOptional.isPresent()) {
                Sales sales = salesOptional.get();
                logger.info("Sale with ID: {} found", id);
                return ResponseEntity.ok(new StandardResponse<>(true, HttpStatus.OK.value(), sales, "Sale found"));
            } else {
                logger.warn("Sale with ID: {} not found", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new StandardResponse<>(false, HttpStatus.NOT_FOUND.value(), null, "Sale not found"));
            }
        } catch (Exception e) {
            logger.error("Error retrieving sale with ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error retrieving sale"));
        }
    }

    @PostMapping("/car")
    public ResponseEntity<StandardResponse<Car>> createCar(@Valid @RequestBody CarDTO carDTO) {
        try {
            Car createdCar = saleDataService.save(new Car(
                    carDTO.getCity(),
                    carDTO.getColor(),
                    carDTO.getState(),
                    carDTO.getDrivetrain(),
                    carDTO.getMpg(),
                    carDTO.getTransmission(),
                    carDTO.getEngine(),
                    carDTO.getVin(),
                    carDTO.getMileage(),
                    carDTO.getPrice()
            ));
            logger.info("Car created successfully with VIN: {}", carDTO.getVin());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new StandardResponse<>(true, HttpStatus.OK.value(), createdCar, "Car was created successfully."));

        } catch (Exception e) {
            logger.error("Error creating car: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse<>(false, HttpStatus.OK.value(), null, "Error creating car"));
        }
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<StandardResponse<Car>> getCarById(@PathVariable int id) {
        try {
            Optional<Car> car = saleDataService.getCarById(id);
            if (car.isPresent()) {
                logger.info("Car with ID: {} was retrieved successfully.", id);
                return ResponseEntity.ok()
                        .body(new StandardResponse<>(true, HttpStatus.OK.value(), car.get(), "Car was retrieved successfully."));
            } else {
                logger.warn("Car with ID: {} not found", id);
                throw new CarNotFoundException("Car with ID: " + id + " not found");
            }
        } catch (CarNotFoundException e) {
            logger.error("CarNotFoundException occurred: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An unexpected error occurred while retrieving car with ID: {}", id, e);
            throw new RuntimeException("Error retrieving car");
        }
    }
}
