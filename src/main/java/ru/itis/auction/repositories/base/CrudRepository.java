package ru.itis.auction.repositories.base;



import ru.itis.auction.models.Auction;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T model);
    List<T> findAll();
    void delete(T model);
    void update(T model);
    Optional<T> findByName(String name);

}
