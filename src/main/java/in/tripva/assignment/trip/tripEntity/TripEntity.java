package in.tripva.assignment.trip.tripEntity;

import in.tripva.assignment.bus.busEntity.BusEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "trip")
public class TripEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;
    private String source;
    private String destination;
    private double fare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id",nullable = false)
    private BusEntity bus;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private LocalDate date;


}
