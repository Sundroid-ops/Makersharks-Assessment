package com.Internship.Assignment.Repository;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import com.Internship.Assignment.Entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, UUID> {
    @Query("SELECT s FROM Supplier s JOIN s.manufacturing_processes mp WHERE s.location=:location AND s.nature_of_business=:business AND mp IN :manufacture")
    Page<Supplier> getSuppliersByLocationAndBusinessAndManufacture
            (@Param("location") String location, @Param("business") Business business, @Param("manufacture") Set<Manufacture> manufacture, Pageable pageable);
}
