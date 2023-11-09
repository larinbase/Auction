package ru.itis.auction.repositories;

import ru.itis.auction.models.Auction;
import ru.itis.auction.models.User;
import ru.itis.auction.repositories.base.CrudRepository;
import ru.itis.auction.utils.mappers.row.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuctionRepository extends CrudRepository<Auction> {

    default List<Auction> extract(RowMapper<Auction> rowMapper, ResultSet resultSet) throws SQLException {
        Boolean next = resultSet.next();
        List<Auction> entities = new ArrayList<>();
        int i = 0;
        while (next) {
            entities.add(rowMapper.from(resultSet, i));
            next = resultSet.next();
            i++;
        }
        return entities;
    }

    Optional<Auction> findByUserId(UUID userId);
    Optional<Auction> findById(Integer id);
}
