package com.Internship.Assignment.Service;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Repository.SupplierRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class SupplierServiceImplTest {

    @MockBean
    private SupplierRepo supplierRepo;

    @BeforeEach
    void setUp() {
        Supplier supplier1 = Supplier.builder()
                .supplier_id(UUID.randomUUID())
                .company_name("abc")
                .location("India")
                .website("www.abc.com")
                .nature_of_business(Business.large_scale)
                .manufacturing_processes(Manufacture.casting)
                .build();

        Supplier supplier2 = Supplier.builder()
                .supplier_id(UUID.randomUUID())
                .company_name("xyz")
                .location("India")
                .website("www.xyz.com")
                .nature_of_business(Business.large_scale)
                .manufacturing_processes(Manufacture.casting)
                .build();

        List<Supplier> supplierList = Arrays.asList(supplier1, supplier2);

        Page<Supplier> supplierPage = new PageImpl<>(supplierList, PageRequest.of(0, 10), supplierList.size());


        Mockito.when(supplierRepo.
                getSuppliersByLocationAndBusinessAndManufacture
                        ("India", Business.large_scale, Manufacture.casting, PageRequest.of(0, 10)))
                .thenReturn(supplierPage);

    }

    @Test
    void searchingSuppliersWithValidOutput() {
        Page<Supplier> supplierPage = supplierRepo
                .getSuppliersByLocationAndBusinessAndManufacture("India", Business.large_scale, Manufacture.casting, PageRequest.of(0, 10));

        assertNotNull(supplierPage);
        assertEquals(2, supplierPage.getTotalElements());
        assertEquals("xyz", supplierPage.getContent().get(1).getCompany_name());
        assertEquals(Manufacture.casting, supplierPage.getContent().get(0).getManufacturing_processes());
    }
}