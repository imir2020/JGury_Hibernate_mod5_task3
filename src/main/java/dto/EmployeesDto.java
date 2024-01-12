package dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class EmployeesDto {
    @NotNull
    Long id;

    @NotBlank
    String lastName;

    @NotBlank
    String name;

    @NotBlank
    String middleName;

    @NotNull
    LocalDate dateBirth;

    @NotEmpty
    String phoneNumber;

    @NotBlank
    String address;

    @NotNull
    Long rankId;
}
