package dto;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class ToBaseEmployeeDto {
    @NotBlank
    String lastName;

    @NotBlank
    String name;

    @NotBlank
    String middleName;

    @NotEmpty
    String dateBirth;

    @NotEmpty
    String phoneNumber;

    @NotEmpty
    String address;

    @NotEmpty
    Long rankId;
}
