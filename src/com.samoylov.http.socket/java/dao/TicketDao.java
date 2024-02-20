package dao;

import entity.Ticket;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {
    private static final TicketDao INSTANCE = new TicketDao();

    private static final String FIND_ALL_BY_FLIGHT_ID_SQL = """
            SELECT * 
            FROM ticket
            WHERE flight_id = ?
            """;

    public static TicketDao getInstance() {
        return INSTANCE;
    }

    private TicketDao() {
    }


    public List<Ticket> findAllByFlightId(Long flightId) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_FLIGHT_ID_SQL)) {
            preparedStatement.setObject(1, flightId);

            var resultSet = preparedStatement.executeQuery();
            List<Ticket> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(buildTicket(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private Ticket buildTicket(ResultSet resultSet) {
        return Ticket.builder()
                .id(resultSet.getObject("id", Long.class))
                .passengerNo(resultSet.getObject("passenger_no", String.class))
                .passengerName(resultSet.getObject("passenger_name", String.class))
                .flightId(resultSet.getObject("flight_id", Long.class))
                .seatNo(resultSet.getObject("seat_no", String.class))
                .cost(resultSet.getObject("cost", BigDecimal.class))
                .build();
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
