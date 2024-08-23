package com.Internship.Assignment.Entity;

import com.Internship.Assignment.Entity.Enums.Business;
import com.Internship.Assignment.Entity.Enums.Manufacture;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_supplier")
public class Supplier {
    @Id
    private UUID supplier_id;

    @Length(min = 4, max = 20, message = "Name should have more than 3 and less than 20 characters")
    @NotEmpty(message = "name cannot be empty")
    @NotNull(message = "name cannot be set to null")
    private String company_name;

    @NotEmpty(message = "website cannot be empty")
    @NotNull(message = "website cannot be set to null")
    private String website;

    @NotEmpty(message = "location cannot be empty")
    @NotNull(message = "location cannot be set to null")
    private String location;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "business cannot be set to null")
    private Business nature_of_business;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "manufacture cannot be set to null")
    @ElementCollection
    private Set<Manufacture> manufacturing_processes;

    public void addManufacturingProcesses(Set<Manufacture> manufacturingProcesses){
        if(manufacturing_processes == null)
            manufacturing_processes = new HashSet<>();

        manufacturing_processes.addAll(manufacturingProcesses);
    }
}
