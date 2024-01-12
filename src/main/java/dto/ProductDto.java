package dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record ProductDto(
        @NotNull
        Long id,
        @NotEmpty
        String nameAndCount) {

}
