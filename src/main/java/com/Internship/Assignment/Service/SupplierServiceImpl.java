package com.Internship.Assignment.Service;

import com.Internship.Assignment.DTO.SupplierDTO;
import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Exception.InternalServerException;
import com.Internship.Assignment.Exception.SupplierNotFoundException;
import com.Internship.Assignment.Repository.SupplierRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepo supplierRepo;

    private static final Logger logs = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Override
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

    @Override
    public Supplier getSupplierByID(UUID supplierID) {
        logs.info("Finding supplier by ID : {}", supplierID);
        Supplier getSupplier = supplierRepo.findById(supplierID)
                .orElseThrow(() -> new SupplierNotFoundException("No Supplier Data Found for ID : " + supplierID));
        logs.info("Supplier found : {}", getSupplier);

        return getSupplier;
    }

    @Override
    public List<Supplier> searchSuppliers
            (String location, Business business, Manufacture manufacture, int page, int size) {
        Page<Supplier> getSuppliersPagination;
        logs.info("searching Suppliers by location, business and manufacture queries");
        try {
            getSuppliersPagination = supplierRepo.getSuppliersByLocationAndBusinessAndManufacture(location, business, manufacture, PageRequest.of(page, size));

        }catch (Exception exception){
            logs.error("Error occurred during querying for suppliers");
            throw new InternalServerException("Error while searching suppliers by querying");
        }

        logs.info("search completed for finding page of suppliers by querying");

        return getSuppliersPagination.getContent();
    }
}
