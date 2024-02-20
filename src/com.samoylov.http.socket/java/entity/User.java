package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    Integer id;
    String name;
    String image;
    LocalDate birthday;
    String email;
    String password;
    Role role;
    Gender gender;
}
