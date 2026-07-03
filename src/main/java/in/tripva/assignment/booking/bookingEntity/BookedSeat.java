package in.tripva.assignment.booking.bookingEntity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookedSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookedSeatId;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    private String seatNumber;

}
