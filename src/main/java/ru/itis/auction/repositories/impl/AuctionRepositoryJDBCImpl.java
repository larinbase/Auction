package ru.itis.auction.repositories.impl;

import ru.itis.auction.models.Auction;
import ru.itis.auction.repositories.AuctionRepository;
import ru.itis.auction.utils.mappers.row.RowMapper;
import ru.itis.auction.utils.mappers.row.impl.AuctionRowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AuctionRepositoryJDBCImpl implements AuctionRepository {
    final String HOST = "jdbc:postgresql://localhost:5432/AuctionDB";
    final String USER = "postgres";
    final String PASS = "1234";
    //language=sql
    private static final String SQL_SELECT_ALL = "select * from auction where auction.status = 'open'";
    //language=sql
    private static final String SQL_SAVE = "insert into auction(name, account_id) values(?, ?)";
    //language=sql
    private static final String SQL_GET_BY_USER_ID = "select * from auction where account_id = ? and status='open'";
    //language=sql
    private static final String SQL_GET_BY_ID = "select * from auction where id=?";
    //language=sql
    private static final String SQL_UPDATE = "update auction set status = ? where id = ?";

    @Override
    public void save(Auction model) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, model.getName());
            preparedStatement.setObject(2, model.getUserId());


            int affect = preparedStatement.executeUpdate();

            if (affect != 1) {
                throw new SQLException("Cannot insert account");
            }

            try (ResultSet generatedIds = preparedStatement.getGeneratedKeys()) {
                if (generatedIds.next()) {
                    model.setId(generatedIds.getInt("id"));
                } else {
                    throw new SQLException("Cannot retrieve id");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Auction> findAll() {
        List<Auction> auctionList;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                RowMapper<Auction> rowMapper = new AuctionRowMapper();
                auctionList = extract(rowMapper, resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return auctionList;
    }

    @Override
    public void delete(Auction model) {

    }

    @Override
    public Optional<Auction> findById(Integer id) {
        Optional<Auction> auction;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                RowMapper<Auction> rowMapper = new AuctionRowMapper();
                auction = extract(rowMapper, resultSet).stream().findAny();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return auction;
    }

    @Override
    public void update(Auction model) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, model.getStatus());
            preparedStatement.setInt(2, model.getId());
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Запись успешно обновлена.");
            } else {
                System.out.println("Запись не обновлена. Возможно, запись с указанным идентификатором не найдена.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Auction> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Auction> findByUserId(UUID userId) {
        Optional<Auction> optionalAuction;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_USER_ID)) {

            preparedStatement.setObject(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                RowMapper<Auction> rowMapper = new AuctionRowMapper();
                optionalAuction = extract(rowMapper, resultSet).stream().findAny();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalAuction;
    }
}
