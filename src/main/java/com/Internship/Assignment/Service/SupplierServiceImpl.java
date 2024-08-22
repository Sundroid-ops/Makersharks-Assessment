package com.Internship.Assignment.Service;

import com.Internship.Assignment.DTO.SupplierDTO;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Exception.InternalServerException;
import com.Internship.Assignment.Repository.SupplierRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepo supplierRepo;

    private static final Logger logs = LoggerFactory.getLogger(SupplierServiceImpl.class);

    public Supplier saveSupplier(SupplierDTO supplierDTO){
        Supplier supplier = Supplier.builder()
                .supplier_id(UUID.randomUUID())
                .name(supplierDTO.getName())
                .location(supplierDTO.getLocation())
                .website(supplierDTO.getWebsite())
                .business(supplierDTO.getBusiness())
                .manufacture(supplierDTO.getManufacture())
                .build();

        try {
            logs.info("Attempting to save supplier : {}", supplier);
            Supplier savedSupplier = supplierRepo.save(supplier);
            logs.info("Supplier saved successfully : {}", savedSupplier);
            return savedSupplier;

        }catch (Exception e){
            logs.error("Error saving supplier: {}", e.getMessage());
            throw new InternalServerException("Supplier Data could not be saved");
        }
    }
}
