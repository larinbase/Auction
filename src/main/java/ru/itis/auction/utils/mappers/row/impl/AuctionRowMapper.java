package ru.itis.auction.utils.mappers.row.impl;

import ru.itis.auction.models.Auction;
import ru.itis.auction.models.Bet;
import ru.itis.auction.utils.mappers.row.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AuctionRowMapper implements RowMapper<Auction> {
    @Override
    public Auction from(ResultSet rs, int rowNum) throws SQLException {
        return Auction.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .userId((UUID) rs.getObject("account_id"))
                .status(rs.getString("status"))
                .build();
    }
}
