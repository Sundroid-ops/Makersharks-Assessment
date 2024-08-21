package com.Internship.Assignment.DTO;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class SupplierDTO {
    @Length(min = 4, max = 20, message = "Name should have more than 3 and less than 20 characters")
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be set to null")
    private String name;

    @NotEmpty(message = "location cannot be empty")
    @NotNull(message = "location cannot be set to null")
    private String location;

    @NotEmpty(message = "website cannot be empty")
    @NotNull(message = "website cannot be set to null")
    private String website;

    @NotNull(message = "business cannot be set to null")
    private Business business;

    @NotNull(message = "manufacture cannot be set to null")
    private Manufacture manufacture;
}
