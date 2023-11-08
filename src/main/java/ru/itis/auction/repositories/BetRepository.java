package ru.itis.auction.repositories;

import ru.itis.auction.models.Bet;
import ru.itis.auction.utils.mappers.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BetRepository extends CrudRepository<Bet> {
    default List<Bet> extract(RowMapper<Bet> rowMapper, ResultSet resultSet) throws SQLException {
        Boolean next = resultSet.next();
        List<Bet> entities = new ArrayList<>();
        int i = 0;
        while (next) {
            entities.add(rowMapper.from(resultSet, i));
            next = resultSet.next();
            i++;
        }
        return entities;
    }

    List<Bet> findByLotId(Integer lotId);
}
