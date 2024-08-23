package com.Internship.Assignment.DTO;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
public class SupplierDTO {
    @Length(min = 4, max = 20, message = "Name should have more than 3 and less than 20 characters")
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be set to null")
    private String company_name;

    @NotEmpty(message = "location cannot be empty")
    @NotNull(message = "location cannot be set to null")
    private String location;

    @NotEmpty(message = "website cannot be empty")
    @NotNull(message = "website cannot be set to null")
    private String website;

    @NotNull(message = "business cannot be set to null")
    @Enumerated(EnumType.STRING)
    private Business nature_of_business;

    @NotNull(message = "manufacture cannot be set to null")
    @Enumerated(EnumType.STRING)
    private Set<Manufacture> manufacturing_processes;
}
