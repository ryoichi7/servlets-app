package entity;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class Ticket {
    Long id;
    String passengerNo;
    String passengerName;
    Long flightId;
    String seatNo;
    BigDecimal cost;
}
