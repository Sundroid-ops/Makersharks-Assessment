package com.Internship.Assignment.Service;

import com.Internship.Assignment.DTO.SupplierDTO;
import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import com.Internship.Assignment.Entity.Supplier;

import java.util.List;
import java.util.UUID;

public interface SupplierService{
    public Supplier saveSupplier(SupplierDTO supplierDTO);

    public Supplier getSupplierByID(UUID id);

    public List<Supplier> searchSuppliers
            (String location, Business business, Manufacture manufacture, int page, int size);
}
