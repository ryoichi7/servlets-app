package validator;

import dto.CreateUserDto;
import entity.Gender;
import util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        if (Gender.find(object.getGender()).isEmpty()){
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        if (!LocalDateFormatter.isValid(object.getBirthday())){
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }
        if (object.getName() == null || object.getName().isBlank()){
            validationResult.add(Error.of("invalid.username", "Username is invalid"));
        }
        return validationResult;
    }
    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
