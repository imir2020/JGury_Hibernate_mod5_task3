package validator;

import dto.CreateUserDto;
import entity.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import utils.LocalDateFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        if (object.getName()==null||object.getName().equals("")){
            validationResult.add(Errors.of("invalid.name", "Name is invalid"));
        }
        if (!LocalDateFormatter.isValid(object.getBirthday())){
            validationResult.add(Errors.of("invalid.birthday", "Birthday is invalid"));

        }
        if (object.getPassword()==null||object.getPassword().equals("")){
            validationResult.add(Errors.of("invalid.password","Password is invalid"));
        }
        if (Status.find(object.getStatus()).isEmpty()){
            validationResult.add(Errors.of("invalid.status","Status is invalid"));
        }
        return validationResult;
    }



    public static CreateUserValidator getInstance(){
        return INSTANCE;
    }


}
