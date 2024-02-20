package dto;

import entity.Gender;
import entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ReadUserDto {
    Integer id;
    String username;
    LocalDate birthday;
    String email;
    String image;
    Role role;
    Gender gender;
}
