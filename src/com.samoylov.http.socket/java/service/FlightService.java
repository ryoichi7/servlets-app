package service;

import dao.FlightDao;
import dto.FlightDto;
import entity.Flight;

import java.util.List;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();

    private FlightService(){}
    private final FlightDao flightDao = FlightDao.getInstance();

    public List<FlightDto> findAll() {
        return flightDao.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private FlightDto mapToDto(Flight entity) {
        return FlightDto.builder()
                .id(entity.getId())
                .description( "%s - %s - %s"
                .formatted(
                entity.getDepartureAirportCode(),
                entity.getArrivalAirportCode(),
                entity.getStatus()))
                .build();
    }

    public static FlightService getInstance() {
        return INSTANCE;
    }
}
