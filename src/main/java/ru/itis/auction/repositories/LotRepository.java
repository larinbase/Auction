package ru.itis.auction.repositories;

import ru.itis.auction.models.Lot;
import ru.itis.auction.repositories.base.CrudRepository;
import ru.itis.auction.utils.mappers.row.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LotRepository extends CrudRepository<Lot> {

    default List<Lot> extract(RowMapper<Lot> rowMapper, ResultSet resultSet) throws SQLException {
        Boolean next = resultSet.next();
        List<Lot> entities = new ArrayList<>();
        int i = 0;
        while (next) {
            entities.add(rowMapper.from(resultSet, i));
            next = resultSet.next();
            i++;
        }
        return entities;
    }

    Optional<Lot> findByArtikul(String artikul);
    Optional<Lot> findById(Integer id);
    Optional<List<Lot>> findByAuctionId(Integer auctionId);

}
