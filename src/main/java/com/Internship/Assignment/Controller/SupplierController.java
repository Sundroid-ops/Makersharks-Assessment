package com.Internship.Assignment.Controller;

import com.Internship.Assignment.DTO.SupplierDTO;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Service.SupplierService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    private static final Logger logs = LoggerFactory.getLogger(SupplierController.class);

    @PostMapping("/create")
    public ResponseEntity<Supplier> saveSupplier(@Valid @RequestBody SupplierDTO supplierDTO){
        logs.info("Incoming Request for saving new Supplier Data : {}", supplierDTO);
        Supplier savedSupplier = supplierService.saveSupplier(supplierDTO);
        logs.info("Supplier Successfully created: {}", savedSupplier);

        return ResponseEntity.ok().body(savedSupplier);
    }

    @GetMapping("/{supplierID}")
    public ResponseEntity<Supplier> getSupplierByID(@NotNull @PathVariable UUID supplierID){
        logs.info("Incoming request to search for supplier by ID : {}", supplierID);
        Supplier getSupplier = supplierService.getSupplierByID(supplierID);
        logs.info("Supplier found by ID : {}", getSupplier);

        return ResponseEntity.ok().body(getSupplier);
    }
}
