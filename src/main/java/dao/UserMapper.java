package dao;

import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User.Builder()
                .setId(rs.getInt("id"))
                .setFirstName(rs.getString("firstname"))
                .setSecondName(rs.getString("secondname"))
                .setAge(rs.getInt("age"))
                .build();
        return user;
    }
}
