package in.tripva.assignment.trip.tripDTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchTripDTO {

    private String source;
    private String destination;
    private LocalDate date;

}
