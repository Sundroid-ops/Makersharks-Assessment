package com.Internship.Assignment.Controller;

import com.Internship.Assignment.DTO.SupplierDTO;
import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Service.SupplierService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    private static final Logger logs = LoggerFactory.getLogger(SupplierController.class);

    @PostMapping("/create")
    public ResponseEntity<Supplier> saveSupplier(@Valid @RequestBody SupplierDTO supplierDTO){
        logs.info("Incoming POST Request for saving new Supplier Data : {}", supplierDTO);
        Supplier savedSupplier = supplierService.saveSupplier(supplierDTO);
        logs.info("Supplier Successfully created: {}", savedSupplier);

        return ResponseEntity.ok().body(savedSupplier);
    }

    @GetMapping("/{supplierID}")
    public ResponseEntity<Supplier> getSupplierByID(@NotNull @PathVariable UUID supplierID){
        logs.info("Incoming GET request to search for supplier by ID : {}", supplierID);
        Supplier getSupplier = supplierService.getSupplierByID(supplierID);
        logs.info("Supplier found by ID : {}", getSupplier);

        return ResponseEntity.ok().body(getSupplier);
    }

    @PostMapping("/query")
    public ResponseEntity<List<Supplier>> searchSuppliers(
            @NotNull @RequestParam String location,
            @NotNull @RequestParam Business business,
            @NotNull @RequestParam Set<Manufacture> manufacture,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        logs.info("Incoming POST request for searching suppliers by querying");
        List<Supplier> getSuppliersPage  = supplierService.searchSuppliers(location, business, manufacture, page, size);

        logs.info("Supplier Data found after querying");
        return ResponseEntity.ok().body(getSuppliersPage);
    }
}
