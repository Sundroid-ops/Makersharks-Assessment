package com.Internship.Assignment.Controller;

import com.Internship.Assignment.DTO.SupplierDTO;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Service.SupplierService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        logs.info("Supplier Succesfully created: {}", savedSupplier);

        return ResponseEntity.ok().body(savedSupplier);
    }
}
