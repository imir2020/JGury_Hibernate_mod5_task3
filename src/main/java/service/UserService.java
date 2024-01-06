package service;

import dao.UserDao;
import dto.CreateUserDto;

import dto.UserDto;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapper.CreateUserMapper;
import mapper.UserToUserDtoMapper;
import validator.CreateUserValidator;

import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private final static UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserToUserDtoMapper userToUserDtoMapper = UserToUserDtoMapper.getInstance();

    public Optional<UserDto> login(String password) {
        Optional<UserDto>  result = userDao.findByPassword(password)
                .map(userToUserDtoMapper::mapFrom);
        if (result.isEmpty()){
            log.warn("The password is not exist: {}",password);
        }else {
            log.info("The User with name {} was login",result.get().getName());
        }
        return result;
    }
    public Integer create(CreateUserDto createUserDto) {
        var validationResult = createUserValidator.isValid(createUserDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }


        var user = createUserMapper.mapFrom(createUserDto);
        var result = userDao.save(user);
        return result.getId();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
