package mapper;

import dto.CreateUserDto;
import dto.UserDto;
import entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToUserMapper {
    DtoToUserMapper INSTANCE = Mappers.getMapper(DtoToUserMapper.class);

    User dtoToUser(CreateUserDto userDto);
}
