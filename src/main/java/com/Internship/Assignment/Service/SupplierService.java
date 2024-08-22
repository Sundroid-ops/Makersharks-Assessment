package com.Internship.Assignment.Service;

import com.Internship.Assignment.DTO.SupplierDTO;
import com.Internship.Assignment.Entity.Supplier;

import java.util.UUID;

public interface SupplierService{
    public Supplier saveSupplier(SupplierDTO supplierDTO);

    public Supplier getSupplierByID(UUID id);
}
