package exception;

import lombok.Getter;
import validator.Errors;


import java.util.List;

public class ValidationException extends RuntimeException {

    @Getter
    private final List<Errors> errors;

    public ValidationException(List<Errors> errors) {
        this.errors = errors;
    }
}
