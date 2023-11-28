package dao;

import entity.Status;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import utils.StatementUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UserDao implements Dao<Long, User> {
    private final static UserDao INSTANCE = new UserDao();
    private static String SAVE_SQL = """
            INSERT INTO users(name,birthday,password,status)
            VALUES(?,?,?,?)
            """;
    private static String GET_PASSWORD_SQL = """
            SELECT * FROM users WHERE password=?
            """;

    public static UserDao getInstance() {
        return INSTANCE;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .password(resultSet.getObject("password", String.class))
                .status(Status.find(resultSet.getObject("status", String.class)).orElse(null))
                .build();
    }


    @SneakyThrows
    public Optional<User> findByPassword(String password) {
        try (var preparedStatement = StatementUtil.getStatement(GET_PASSWORD_SQL)) {
            preparedStatement.setString(1, password);

            var resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @Override
    @SneakyThrows
    public User save(User user) {
        try (var preparedStatement = StatementUtil.getStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, user.getName());
            preparedStatement.setObject(2, user.getBirthday());
            preparedStatement.setObject(3, user.getPassword());
            preparedStatement.setObject(4, user.getStatus().name());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getObject("id", Integer.class));
            }
            return user;
        }
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
