package validator;

import lombok.Value;

@Value(staticConstructor = "of")
public final class Errors {
    String code;
    String message;
}
