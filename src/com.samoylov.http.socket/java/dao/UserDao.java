package dao;

import entity.Gender;
import entity.Role;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements Dao<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users(name, birthday, email, password, role, gender, image)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = """
             SELECT * FROM users WHERE email = ? and password = ?
            """;

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);

            var resultSet = preparedStatement.executeQuery();

            User user = null;
            if (resultSet.next()){
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @SneakyThrows
    private User buildUser(ResultSet resultSet) {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .image(resultSet.getObject("image", String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .gender(Gender.find(resultSet.getObject("gender", String.class)).orElse(null))
                .name(resultSet.getObject("name", String.class))
                .password(resultSet.getObject("password", String.class))
                .email(resultSet.getObject("email", String.class))
                .build();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getRole().name());
            preparedStatement.setObject(6, entity.getGender().name());
            preparedStatement.setObject(7, entity.getImage());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId((generatedKeys.getObject("id", Integer.class)));
            return entity;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
