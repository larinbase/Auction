package ru.itis.auction.repositories.impl;

import ru.itis.auction.models.Award;
import ru.itis.auction.models.Bet;
import ru.itis.auction.repositories.AwardRepository;
import ru.itis.auction.utils.mappers.AwardRowMapper;
import ru.itis.auction.utils.mappers.BetRowMapper;
import ru.itis.auction.utils.mappers.RowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AwardRepositoryJDBCImpl implements AwardRepository {
    final String HOST = "jdbc:postgresql://localhost:5432/AuctionDB";
    final String USER = "postgres";
    final String PASS = "1234";
    //language=sql
    private static String SQL_SELECT_ALL = "select * from award";
    //language=sql
    private static String SQL_SAVE = "insert into award(lot_id, account_id) values(?, ?)";
    //language=sql
    private static final String SQL_DELETE_BY_ID = "delete from award where id = ?";
    //language=sql
    private static final String SQL_GET_BY_LOT_ID = "select * from award where lot_id = ?";
    //language=sql
    private static final String SQL_GET_BY_USER_ID = "select * from award where  account_id = ?";
    //language=sql
    //private static final String SQL_UPDATE = "update award set amount = ? where id = ?";


    @Override
    public void save(Award model) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, model.getLotId());
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
    public List<Award> findAll() {
        return null;
    }

    @Override
    public void delete(Award model) {

    }

    @Override
    public void update(Award model) {

    }

    @Override
    public Optional<Award> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Award>> findByUserId(UUID userId) {
        Optional<List<Award>> awards;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_USER_ID)) {

            preparedStatement.setObject(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                RowMapper<Award> rowMapper = new AwardRowMapper();
                awards = Optional.of(extract(rowMapper, resultSet));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return awards;
    }
}
