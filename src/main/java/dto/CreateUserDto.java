package dto;


import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class CreateUserDto {
    @NotNull
    String name;
    @NotNull
    String birthday;
    @NotEmpty
    String password;
    @NotEmpty
    String status;
}
