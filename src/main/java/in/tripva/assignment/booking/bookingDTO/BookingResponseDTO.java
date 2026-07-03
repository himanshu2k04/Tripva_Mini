package in.tripva.assignment.booking.bookingDTO;

import in.tripva.assignment.booking.bookingEntity.Status;
import in.tripva.assignment.trip.tripEntity.TripEntity;
import in.tripva.assignment.user.userEntity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponseDTO {

    private Long bookingId;

    private UserEntity user;

    private TripEntity trip;

    private List<String> seatNumbers;
    private Status status;

    private Double totalAmount;
    private LocalDateTime createdAt;

}
