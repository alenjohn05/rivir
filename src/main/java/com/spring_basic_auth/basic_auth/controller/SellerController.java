package com.spring_basic_auth.basic_auth.controller;

import com.spring_basic_auth.basic_auth.dtomodel.SaleDTO;
import com.spring_basic_auth.basic_auth.dtomodel.StandardResponse;
import com.spring_basic_auth.basic_auth.model.Sales;
import com.spring_basic_auth.basic_auth.service.SaleDataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerController {

    public final SaleDataService saleDataService;

    public SellerController(SaleDataService saleDataService) {
        this.saleDataService = saleDataService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> findHealth() {
        return ResponseEntity.ok("Sale running successfully!"); // Adjust response as needed
    }

    @PostMapping("/")
    public ResponseEntity<StandardResponse<Sales>> createSale(@Valid @RequestBody SaleDTO saleDTO) {
        try {
            Sales createdSale = saleDataService.createSale(saleDTO);
            if (createdSale != null) {
                StandardResponse<Sales> response = new StandardResponse<>(true, HttpStatus.OK.value(), createdSale, "Sale created successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse<>(false, HttpStatus.NOT_FOUND.value(), null, "Failed to create sale"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new StandardResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse<Sales>> getSales(@PathVariable int id) {
        try {
            Optional<Sales> salesOptional = saleDataService.findSaleById(id);
            if (salesOptional.isPresent()) {
                Sales sales = salesOptional.get();
                return ResponseEntity.ok(new StandardResponse<>(true, HttpStatus.OK.value(), sales, "Sale found"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new StandardResponse<>(false, HttpStatus.NOT_FOUND.value(), null, "Sale not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error retrieving sale"));
        }
    }
}
