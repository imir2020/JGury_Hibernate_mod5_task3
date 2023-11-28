package validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    @Getter
    private final List<Errors> errors = new ArrayList<>();

    public void add(Errors error){
        this.errors.add(error);
    }

    public boolean isValid(){
        return  errors.isEmpty();
    }

}
