package com.Internship.Assignment.Controller;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import com.Internship.Assignment.Entity.Supplier;
import com.Internship.Assignment.Service.SupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SupplierController.class)
class SupplierControllerTest {
    @MockBean
    private SupplierService supplierService;

    @Autowired
    private MockMvc mockMvc;

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

        Set<Manufacture> manufactureSet = new HashSet<>();
        manufactureSet.add(Manufacture.moulding);

        List<Supplier> supplierList = Arrays.asList(supplier1, supplier2);

        Mockito.when(supplierService.searchSuppliers(
                "USA", Business.small_scale, manufactureSet, 0, 10))
                .thenReturn(supplierList);
    }

    @Test
    void searchSuppliersWithValidInputs() throws Exception {
        mockMvc.perform(post("/api/supplier/query")
                .param("location", "USA")
                .param("business", "small_scale")
                .param("manufacture", "moulding")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].company_name").value("abcd"));
    }

    @Test
    void searchSuppliersWithInvalidInputs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/supplier/query")
                .param("location", "USA")
                .param("business", "small_large")
                .param("manufacture", "mouldingssss")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}