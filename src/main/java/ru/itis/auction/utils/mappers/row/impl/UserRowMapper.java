package ru.itis.auction.utils.mappers.row.impl;

import ru.itis.auction.models.User;
import ru.itis.auction.utils.mappers.row.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User from(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id((UUID) rs.getObject("id"))
                .name(rs.getString("name"))
                .password(rs.getString("password"))
                .build();
    }
}
