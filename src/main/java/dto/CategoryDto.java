package dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record CategoryDto(
        @NotNull
        Long category,
        @NotEmpty
        String categoryName) {

}
