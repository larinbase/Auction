package ru.itis.auction.utils.mappers.row.impl;

import ru.itis.auction.models.Bet;
import ru.itis.auction.utils.mappers.row.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BetRowMapper implements RowMapper<Bet> {

    @Override
    public Bet from(ResultSet rs, int rowNum) throws SQLException {
        return Bet.builder()
                .id(rs.getInt("id"))
                .lotId(rs.getInt("lot_id"))
                .amount(rs.getDouble("amount"))
                .userId((UUID) rs.getObject("account_id"))
                .dateTime(rs.getDate("date_time"))
                .build();
    }
}
