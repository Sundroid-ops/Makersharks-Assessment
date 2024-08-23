package com.Internship.Assignment.Repository;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import com.Internship.Assignment.Entity.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SupplierRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SupplierRepo supplierRepo;

    @BeforeEach
    void setUp() {
        Supplier supplier1 = Supplier.builder()
                .supplier_id(UUID.randomUUID())
                .company_name("abcd")
                .location("USA")
                .website("www.abcd.com")
                .nature_of_business(Business.small_scale)
                .build();
        Set<Manufacture> manufactureSet1 = new HashSet<>();
        manufactureSet1.add(Manufacture.moulding);
        supplier1.addManufacturingProcesses(manufactureSet1);

        Supplier supplier2 = Supplier.builder()
                .supplier_id(UUID.randomUUID())
                .company_name("wxyz")
                .location("USA")
                .website("www.wxyz.com")
                .nature_of_business(Business.small_scale)
                .build();
        Set<Manufacture> manufactureSet2 = new HashSet<>();
        manufactureSet2.add(Manufacture.moulding);
        manufactureSet2.add(Manufacture.printing_3d);
        supplier2.addManufacturingProcesses(manufactureSet2);


        Supplier supplier3 = Supplier.builder()
                .supplier_id(UUID.randomUUID())
                .company_name("qwerty")
                .location("INDIA")
                .website("www.qwerty.com")
                .nature_of_business(Business.large_scale)
                .build();
        Set<Manufacture> manufactureSet3 = new HashSet<>();
        manufactureSet3.add(Manufacture.casting);
        manufactureSet3.add(Manufacture.printing_3d);
        manufactureSet3.add(Manufacture.moulding);
        supplier3.addManufacturingProcesses(manufactureSet3);

        entityManager.persist(supplier1);
        entityManager.persist(supplier2);
        entityManager.persist(supplier3);
    }

    @Test
    void getPageOfSuppliersByQueryingWithOneCommonDataPresentInManufacturingProcesses(){
        Set<Manufacture> manufactureSet = new HashSet<>();
        manufactureSet.add(Manufacture.moulding);
        Page<Supplier> supplierPage = supplierRepo.getSuppliersByLocationAndBusinessAndManufacture
                ("USA", Business.small_scale, manufactureSet, PageRequest.of(1, 1));

        assertEquals(2, supplierPage.getTotalPages());
        assertEquals(2, supplierPage.getTotalElements());
        assertEquals("USA", supplierPage.getContent().get(0).getLocation());
        assertEquals(Business.small_scale, supplierPage.getContent().get(0).getNature_of_business());
    }

    @Test
    void getOneSupplierAfterQuerying(){
        Set<Manufacture> manufactureSet = new HashSet<>();
        manufactureSet.add(Manufacture.casting);

        Page<Supplier> supplierPage = supplierRepo.getSuppliersByLocationAndBusinessAndManufacture
                ("INDIA", Business.large_scale, manufactureSet, PageRequest.of(0, 10));

        assertEquals(1, supplierPage.getTotalPages());
        assertEquals(1, supplierPage.getTotalElements());
        assertEquals("qwerty", supplierPage.getContent().get(0).getCompany_name());
        assertEquals(3, supplierPage.getContent().get(0).getManufacturing_processes().size());
    }
}