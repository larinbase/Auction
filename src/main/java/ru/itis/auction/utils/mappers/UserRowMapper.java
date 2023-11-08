package ru.itis.auction.utils.mappers;

import ru.itis.auction.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User>{
    @Override
    public User from(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id((UUID) rs.getObject("id"))
                .name(rs.getString("name"))
                .password(rs.getString("password"))
                .role(rs.getString("role"))
                .build();
    }
}
