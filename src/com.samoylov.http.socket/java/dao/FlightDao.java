package dao;

import entity.Flight;
import entity.FlightStatus;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {

    private FlightDao() {

    }

    private static final FlightDao INSTANCE = new FlightDao();
    private static final String FIND_ALL_SQL = """
            SELECT * 
            FROM flight
                """;

    @Override
    public List<Flight> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Flight> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(buildFlight(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @SneakyThrows
    private static Flight buildFlight(ResultSet resultSet) {
        return Flight.builder()
                .id(resultSet.getObject("id", Long.class))
                .flightNo(resultSet.getObject("flight_no", String.class))
                .departureDate(resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime().toLocalDate())
                .departureAirportCode(resultSet.getObject("departure_airport_code", String.class))
                .arrivalDate(resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime().toLocalDate())
                .arrivalAirportCode(resultSet.getObject("arrival_airport_code", String.class))
                .aircraftId(resultSet.getObject("aircraft_id", Integer.class))
                .status(FlightStatus.valueOf(resultSet.getObject("status", String.class)))
                .build();
    }

    public static FlightDao getInstance() {
        return INSTANCE;
    }
}
