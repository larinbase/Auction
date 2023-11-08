package ru.itis.auction.utils.mappers;

import ru.itis.auction.models.Award;
import ru.itis.auction.models.Bet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AwardRowMapper implements RowMapper<Award> {

    @Override
    public Award from(ResultSet rs, int rowNum) throws SQLException {
        return Award.builder()
                .id(rs.getInt("id"))
                .userId((UUID) rs.getObject("account_id"))
                .lotId(rs.getInt("lot_id"))
                .build();
    }
}
