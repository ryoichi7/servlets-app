package entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
public class Flight {
    Long id;
    String flightNo;
    LocalDate departureDate;
    String departureAirportCode;
    LocalDate arrivalDate;
    String arrivalAirportCode;
    Integer aircraftId;
    FlightStatus status;
}
