package mapper;

import dto.CreateUserDto;
import entity.Status;
import entity.User;
import utils.LocalDateFormatter;

public class CreateUserMapper implements Mapper<User, CreateUserDto> {
private  static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .birthday(LocalDateFormatter.format(userDto.getBirthday()))
                .password(userDto.getPassword())
                .status(Status.valueOf(userDto.getStatus()))
                .build();
    }

    public static CreateUserMapper getInstance(){
        return INSTANCE;
    }
}
