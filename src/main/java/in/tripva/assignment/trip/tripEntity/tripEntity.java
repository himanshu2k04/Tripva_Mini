package in.tripva.assignment.trip.tripEntity;

import in.tripva.assignment.bus.busEntity.busEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "trip")
public class tripEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;
    private String source;
    private String destination;
    private double fare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id",nullable = false)
    private busEntity bus;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private LocalDate date;


}
