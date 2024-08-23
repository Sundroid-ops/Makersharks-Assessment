package com.Internship.Assignment.Service;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Exception.SupplierNotFoundException;
import com.Internship.Assignment.Repository.SupplierRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SupplierServiceImplTest {

    @MockBean
    private SupplierRepo supplierRepo;

    @InjectMocks
    private SupplierServiceImpl supplierServiceImpl;

    List<Supplier> supplierList;

    @BeforeEach
    void setUp() {
        Supplier supplier1 = Supplier.builder()
                .supplier_id(UUID.randomUUID())
                .company_name("abcd")
                .location("India")
                .website("www.abcd.com")
                .nature_of_business(Business.large_scale)
                .build();
        Set<Manufacture> manufactureSet1 = new HashSet<>();
        manufactureSet1.add(Manufacture.casting);
        manufactureSet1.add(Manufacture.printing_3d);
        supplier1.addManufacturingProcesses(manufactureSet1);

        Supplier supplier2 = Supplier.builder()
                .supplier_id(UUID.randomUUID())
                .company_name("wxyz")
                .location("India")
                .website("www.wxyz.com")
                .nature_of_business(Business.large_scale)
                .build();
        Set<Manufacture> manufactureSet2 = new HashSet<>();
        manufactureSet2.add(Manufacture.moulding);
        manufactureSet2.add(Manufacture.printing_3d);
        supplier2.addManufacturingProcesses(manufactureSet2);

        supplierList = Arrays.asList(supplier1, supplier2);
    }

    @Test
    void getSupplierWithCommonFields(){
        Set<Manufacture> manufactureSet = new HashSet<>();
        manufactureSet.add(Manufacture.casting);
        manufactureSet.add(Manufacture.moulding);

        Page<Supplier> supplierPages = new PageImpl<>(supplierList, PageRequest.of(1, 1), supplierList.size());

        Mockito.when(supplierRepo.
                        getSuppliersByLocationAndBusinessAndManufacture
                                ("India", Business.large_scale, manufactureSet, PageRequest.of(0, 10)))
                .thenReturn(supplierPages);

        Page<Supplier> supplierPage = supplierRepo
                .getSuppliersByLocationAndBusinessAndManufacture("India", Business.large_scale, manufactureSet, PageRequest.of(0, 10));

        assertNotNull(supplierPage);
        assertEquals(2, supplierPage.getTotalElements());
        assertEquals(2, supplierPage.getTotalPages());
        assertEquals("wxyz", supplierPage.getContent().get(1).getCompany_name());
    }

    @Test
    void getNoSuppliersWithSpecifiedFields(){
        Page<Supplier> emptySupplierPage = Page.empty();

        Set<Manufacture> manufactureSet = new HashSet<>();
        manufactureSet.add(Manufacture.casting);
        manufactureSet.add(Manufacture.moulding);

        Mockito.when(supplierRepo.
                        getSuppliersByLocationAndBusinessAndManufacture
                                ("USA", Business.medium_scale, manufactureSet, PageRequest.of(0, 10)))
                .thenReturn(emptySupplierPage);

        Page<Supplier> resultSupplierPage = supplierRepo.getSuppliersByLocationAndBusinessAndManufacture("USA", Business.medium_scale, manufactureSet, PageRequest.of(0, 10));

        assertEquals(Page.empty(), resultSupplierPage);
    }
}