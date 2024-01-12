package mapper;

import dto.UserDto;
import entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToDtoMapper {
    UserToDtoMapper INSTANCE = Mappers.getMapper(UserToDtoMapper.class);

    UserDto toDto(User user);
}
