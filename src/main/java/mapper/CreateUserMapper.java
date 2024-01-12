package mapper;

import dto.CreateUserDto;
import entity.User;

public class CreateUserMapper {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    public User mapFrom(CreateUserDto userDto) {
        return DtoToUserMapper.INSTANCE.dtoToUser(userDto);
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
