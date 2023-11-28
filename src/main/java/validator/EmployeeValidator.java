package validator;

import dto.ToBaseEmployeeDto;
import utils.LocalDateFormatter;

public class EmployeeValidator implements Validator<ToBaseEmployeeDto> {
    private static final EmployeeValidator INSTANCE = new EmployeeValidator();

    @Override
    public ValidationResult isValid(ToBaseEmployeeDto o) {
        var validationResult = new ValidationResult();
        if (o.getLastName() == null || o.getLastName().equals("")) {
            validationResult.add(Errors.of("invalid.lastName", "LastName is invalid"));
        }
        if (o.getName() == null || o.getName().equals("")) {
            validationResult.add(Errors.of("invalid.name", "Name is invalid"));
        }
        if (o.getMiddleName() == null || o.getMiddleName().equals("")) {
            validationResult.add(Errors.of("invalid.middleName", "MiddleName is invalid"));
        }
        if (!LocalDateFormatter.isValid(o.getDateBirth())) {
            validationResult.add(Errors.of("invalid.birthday", "Birthday is invalid"));
        }

        if (o.getPhoneNumber() == null || o.getPhoneNumber().equals("")) {
            validationResult.add(Errors.of("invalid.phoneNumber", "PhoneNumber is invalid"));
        }
        if (o.getAddress() == null || o.getAddress().equals("")) {
            validationResult.add(Errors.of("invalid.address", "Address is invalid"));
        }
        if (o.getRankId() == null || o.getRankId() <= 0) {
            validationResult.add(Errors.of("invalid.rankId", "RankId is invalid"));
        }
        return validationResult;
    }

    public static EmployeeValidator getInstance() {
        return INSTANCE;
    }
}
