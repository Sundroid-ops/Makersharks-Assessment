package com.Internship.Assignment.Controller;

import com.Internship.Assignment.DTO.SupplierDTO;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Service.SupplierService;
import jakarta.validation.Valid;
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

    @PostMapping("/create")
    public ResponseEntity<Supplier> saveSupplier(@Valid @RequestBody SupplierDTO supplierDTO){
        return ResponseEntity.ok().body(supplierService.saveSupplier(supplierDTO));
    }
}
