package ru.itis.auction.repositories;

import ru.itis.auction.models.Award;
import ru.itis.auction.models.Bet;
import ru.itis.auction.utils.mappers.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AwardRepository extends CrudRepository<Award> {
    default List<Award> extract(RowMapper<Award> rowMapper, ResultSet resultSet) throws SQLException {
        Boolean next = resultSet.next();
        List<Award> entities = new ArrayList<>();
        int i = 0;
        while (next) {
            entities.add(rowMapper.from(resultSet, i));
            next = resultSet.next();
            i++;
        }
        return entities;
    }
    Optional<List<Award>> findByUserId(UUID userId);
}
