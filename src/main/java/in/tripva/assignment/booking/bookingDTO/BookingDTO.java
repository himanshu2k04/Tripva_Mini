package in.tripva.assignment.booking.bookingDTO;

import lombok.Data;

import java.util.List;

@Data
public class BookingDTO {

    private Long userId;
    private Long tripId;
    private List<String> seatNumbers;

}
