package service;


import dao.UserRepository;
import dto.CreateUserDto;
import dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.CreateUserMapper;
import mapper.UserToUserDtoMapper;
import validator.CreateUserValidator;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();

    private final UserToUserDtoMapper userToUserDtoMapper = UserToUserDtoMapper.getInstance();
    private static final UserService INSTANCE = new UserService();
    private final UserRepository userRepository = new UserRepository();

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Optional<UserDto> login(String password) {
        Optional<UserDto> result = userRepository.findByPassword(password)
                .map(userToUserDtoMapper::mapFrom);
        if (result.isEmpty()) {
            log.warn("The password is not exist: {}", password);
        } else {
            log.info("The User with name {} was login", result.get().getName());
        }
        return result;
    }

    public Long create(CreateUserDto createUserDto) {
        var validationFactory = Validation.buildDefaultValidatorFactory();
        var validator = validationFactory.getValidator();
        var validationResult = validator.validate(createUserDto);
        if(!validationResult.isEmpty()){
            throw new ConstraintViolationException(validationResult);
        }
        var user = createUserMapper.mapFrom(createUserDto);
        var result = userRepository.save(user);
        return result.getId();
    }
}
