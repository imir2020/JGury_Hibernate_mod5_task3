package mapper;

import dto.UserDto;
import entity.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-12T17:31:19+0300",
    comments = "version: 1.5.4.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
public class UserToDtoMapperImpl implements UserToDtoMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.name( user.getName() );
        userDto.birthday( user.getBirthday() );
        userDto.status( user.getStatus() );

        return userDto.build();
    }
}
