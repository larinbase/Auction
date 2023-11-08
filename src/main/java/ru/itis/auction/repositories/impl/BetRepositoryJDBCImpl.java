package ru.itis.auction.repositories.impl;

import ru.itis.auction.models.Bet;
import ru.itis.auction.repositories.BetRepository;
import ru.itis.auction.utils.mappers.BetRowMapper;
import ru.itis.auction.utils.mappers.RowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BetRepositoryJDBCImpl implements BetRepository {

    final String HOST = "jdbc:postgresql://localhost:5432/AuctionDB";
    final String USER = "postgres";
    final String PASS = "1234";
    //language=sql
    private static String SQL_SELECT_ALL = "select * from bet";
    //language=sql
    private static String SQL_SAVE = "insert into bet(amount, lot_id, account_id) values(?, ?, ?)";
    //language=sql
    private static final String SQL_DELETE_BY_ID = "delete from bet where id = ?";
    //language=sql
    private static final String SQL_GET_BY_LOT_ID = "select * from bet where lot_id = ?";
    //language=sql
    private static final String SQL_UPDATE = "update bet set amount = ? where id = ?";

    @Override
    public List<Bet> findByLotId(Integer lotId) {
        List<Bet> bets;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_LOT_ID)) {

            preparedStatement.setInt(1, lotId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                RowMapper<Bet> rowMapper = new BetRowMapper();
                bets = extract(rowMapper, resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bets;
    }

    @Override
    public void save(Bet model) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setDouble(1, model.getAmount());
            preparedStatement.setInt(2, model.getLotId());
            preparedStatement.setObject(3, model.getUserId());

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
    public List<Bet> findAll() {
        return null;
    }

    @Override
    public void delete(Bet model) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Bet model) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setDouble(1, model.getAmount());
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
    public Optional<Bet> findByName(String name) {
        return Optional.empty();
    }
}
