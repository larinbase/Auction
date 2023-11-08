package ru.itis.auction.repositories;



import ru.itis.auction.models.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CrudRepository<T> {
    void save(T model);
    List<T> findAll();
    void delete(T model);
    void update(T model);
    Optional<T> findByName(String name);

}
