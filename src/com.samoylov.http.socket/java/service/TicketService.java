package service;

import dao.TicketDao;
import dto.TicketDto;
import entity.Ticket;

import java.util.List;

public class TicketService {
    private static final TicketDao ticketDao = TicketDao.getInstance();

    private static final TicketService INSTANCE = new TicketService();

    private TicketService() {
    }

    public static TicketService getInstance() {
        return INSTANCE;
    }

    public List<TicketDto> findAllByFlightId(Long flightId) {
        return ticketDao.findAllByFlightId(flightId).stream()
                .map(this::mapToDto).toList();
    }

    private TicketDto mapToDto(Ticket entity) {
        return TicketDto.builder()
                .id(entity.getId())
                .flightId(entity.getFlightId())
                .seatNo(entity.getSeatNo())
                .build();
    }
}
