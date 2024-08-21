package com.Internship.Assignment.Entity;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
public class Supplier {
    @Id
    private UUID supplier_id;

    @Length(min = 4, max = 20, message = "Name should have more than 3 and less than 20 characters")
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be set to null")
    private String name;

    @NotEmpty(message = "website cannot be empty")
    @NotNull(message = "website cannot be set to null")
    private String website;

    @NotEmpty(message = "location cannot be empty")
    @NotNull(message = "location cannot be set to null")
    private String location;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "business cannot be empty")
    @NotNull(message = "business cannot be set to null")
    private Business business;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "manufacture cannot be empty")
    @NotNull(message = "manufacture cannot be set to null")
    private Manufacture manufacture;
}
