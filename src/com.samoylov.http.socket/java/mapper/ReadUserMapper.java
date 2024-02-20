package mapper;

import dto.ReadUserDto;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadUserMapper implements Mapper<User, ReadUserDto> {

    private static final ReadUserMapper INSTANCE = new ReadUserMapper();

    public static ReadUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ReadUserDto mapFrom(User object) {
        return ReadUserDto.builder()
                .id(object.getId())
                .email(object.getEmail())
                .image(object.getImage())
                .role(object.getRole())
                .gender(object.getGender())
                .birthday(object.getBirthday())
                .username(object.getName())
                .build();
    }
}
