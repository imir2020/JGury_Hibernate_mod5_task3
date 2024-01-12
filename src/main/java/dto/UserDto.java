package dto;

import entity.Status;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    @NotNull
    Long id;
    @NotBlank
    String name;
    @NotEmpty
    LocalDate birthday;
    @NotEmpty
    Status status;
}
