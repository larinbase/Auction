package ru.itis.auction.repositories;


import ru.itis.auction.models.User;
import ru.itis.auction.utils.mappers.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User>{

    Optional<User> findById(UUID id);
    Optional<User> findByName(String name);

    void delete(User user);
    default List<User> extract(RowMapper<User> rowMapper, ResultSet resultSet) throws SQLException {
        Boolean next = resultSet.next();
        List<User> entities = new ArrayList<>();
        int i = 0;
        while (next) {
            entities.add(rowMapper.from(resultSet, i));
            next = resultSet.next();
            i++;
        }
        return entities;
    }


}
