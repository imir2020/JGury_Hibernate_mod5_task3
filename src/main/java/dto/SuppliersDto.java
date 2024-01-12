package dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record SuppliersDto(
        @NotNull
        Long id,
        @NotBlank
        String description)  {

}
