package ru.itis.auction.utils.mappers;

import ru.itis.auction.models.Lot;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LotRowMapper implements RowMapper<Lot>{
    @Override
    public Lot from(ResultSet rs, int rowNum) throws SQLException {
        return Lot.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .status(rs.getString("status"))
                .artikul(rs.getString("artikul"))
                .build();
    }
}
