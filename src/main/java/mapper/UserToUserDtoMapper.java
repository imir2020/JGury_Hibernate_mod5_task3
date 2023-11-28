package mapper;

import dto.UserDto;
import entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserToUserDtoMapper implements Mapper<UserDto, User> {

    private static final UserToUserDtoMapper INSTANCE = new UserToUserDtoMapper();
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .birthday(object.getBirthday())
                .build();
    }

    public static UserToUserDtoMapper getInstance() {
        return INSTANCE;
    }

}
