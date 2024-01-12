package mapper;

import dto.UserDto;
import entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserToUserDtoMapper {
    private static final UserToUserDtoMapper INSTANCE = new UserToUserDtoMapper();

    public UserDto mapFrom(User object) {
        return UserToDtoMapper.INSTANCE.toDto(object);
    }

    public static UserToUserDtoMapper getInstance() {
        return INSTANCE;
    }

}
