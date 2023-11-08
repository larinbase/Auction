package ru.itis.auction.repositories.impl;

import ru.itis.auction.models.Award;
import ru.itis.auction.models.Lot;
import ru.itis.auction.repositories.LotRepository;
import ru.itis.auction.utils.mappers.AwardRowMapper;
import ru.itis.auction.utils.mappers.LotRowMapper;
import ru.itis.auction.utils.mappers.RowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class LotRepositoryJDBCImpl implements LotRepository {

    final String HOST = "jdbc:postgresql://localhost:5432/AuctionDB";
    final String USER = "postgres";
    final String PASS = "1234";
    //language=sql
    private static final String SQL_SELECT_ALL = "select * from lot where lot.status = 'open'";
    //language=sql
    private static final String SQL_SAVE = "insert into lot(name, description) values(?, ?)";
    //language=sql
    private static final String SQL_GET_BY_NAME = "select * from lot where name = ?";
    //language=sql
    private static final String SQL_DELETE_BY_ID = "delete from lot where id = ?";
    //language=sql
    private static final String SQL_GET_BY_ID = "select * from lot where id = ?";
    //language=sql
    private static final String SQL_GET_BY_ARTIKUL = "select * from lot where artikul = ?";
    //language=sql
    private static final String SQL_UPDATE = "update lot set status = ? where id = ?";

    @Override
    public void update(Lot model) {
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
    public List<Lot> findAll() {
        List<Lot> lots;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {

            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                RowMapper<Lot> rowMapper = new LotRowMapper();
                lots = extract(rowMapper, resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lots;

    }


    @Override
    public void delete(Lot lot) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setInt(1, lot.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Lot> findByName(String name) {
        Optional<Lot> optionalLot;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_NAME)) {

            preparedStatement.setString(1, name);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                RowMapper<Lot> rowMapper = new LotRowMapper();
                optionalLot = extract(rowMapper, resultSet).stream().findAny();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalLot;
    }

    @Override
    public void save(Lot model) {
        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getDescription());


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
    public Optional<Lot> findById(Integer id) {
        Optional<Lot> lot;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)) {

            preparedStatement.setObject(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                RowMapper<Lot> rowMapper = new LotRowMapper();
                lot = extract(rowMapper, resultSet).stream().findAny();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lot;
    }

    @Override
    public Optional<Lot> findByArtikul(String artikul) {
        Optional<Lot> optionalLot;

        try (Connection connection = DriverManager.getConnection(HOST, USER, PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ARTIKUL)) {

            preparedStatement.setString(1, artikul);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                RowMapper<Lot> rowMapper = new LotRowMapper();
                optionalLot = extract(rowMapper, resultSet).stream().findAny();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalLot;
    }
}
